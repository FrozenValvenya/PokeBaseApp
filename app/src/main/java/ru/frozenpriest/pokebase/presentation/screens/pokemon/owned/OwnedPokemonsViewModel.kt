package ru.frozenpriest.pokebase.presentation.screens.pokemon.owned

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.pokemon.GetOwnedPokemonShortUseCase
import timber.log.Timber
import javax.inject.Inject

class OwnedPokemonsViewModel @Inject constructor(
    private val getOwnedPokemonShortUseCase: GetOwnedPokemonShortUseCase
) : ViewModel() {
    val pokemonFlow: MutableStateFlow<List<PokemonShort>> = MutableStateFlow(emptyList())

    fun getPokemon() = viewModelScope.launch {
        val result = getOwnedPokemonShortUseCase.getPokemon()
        Timber.i("Got pokemons, result is $result")

        result.onSuccess {
            pokemonFlow.value = it
        }
    }
}
