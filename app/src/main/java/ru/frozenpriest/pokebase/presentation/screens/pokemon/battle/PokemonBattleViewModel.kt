package ru.frozenpriest.pokebase.presentation.screens.pokemon.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.pokemon.GetDamageUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetPokemonDetailsUseCase
import javax.inject.Inject

@Suppress("UnusedPrivateMember", "LongMethod")
class PokemonBattleViewModel @Inject constructor(
    private val getPokemonDetailsUseCase: GetPokemonDetailsUseCase,
    private val getDamageUseCase: GetDamageUseCase
) : ViewModel() {
    private val _pokemons = MutableLiveData<Pair<PokemonInBattle, PokemonInBattle>>()
    val pokemons: LiveData<Pair<PokemonInBattle, PokemonInBattle>> get() = _pokemons

    private val _turn = MutableLiveData(false)
    val turn: LiveData<Boolean> get() = _turn

    fun toggleTurn() {
        val prevTurn = turn.value ?: false
        _turn.value = !prevTurn
    }

    fun loadPokemon(id1: String, id2: String) {
        viewModelScope.launch {
            val first = async {
                val result = getPokemonDetailsUseCase.getPokemonDetails(id1)
                val pokemon = result.getOrThrow()
                PokemonInBattle(pokemon.stats.hp.value, pokemon)
            }
            val second = async {
                val result = getPokemonDetailsUseCase.getPokemonDetails(id2)
                val pokemon = result.getOrThrow()
                PokemonInBattle(pokemon.stats.hp.value, pokemon)
            }

            _pokemons.postValue(first.await() to second.await())
        }
    }

    fun calculateDamage(move: Move) = viewModelScope.launch {
        if (move.category == Category.Status) return@launch

        pokemons.value?.let {
            if (turn.value == true) {
                val damage = getDamageUseCase.calculateDamage(
                    it.first.pokemon.id.toInt(),
                    it.second.pokemon.id.toInt(),
                    move.id.toInt()
                )

                _pokemons.postValue(it.first to it.second.copy(hp = it.second.hp - damage.getOrThrow()))
            } else {
                val damage = getDamageUseCase.calculateDamage(
                    it.second.pokemon.id.toInt(),
                    it.first.pokemon.id.toInt(),
                    move.id.toInt()
                )

                _pokemons.postValue(it.first.copy(hp = it.first.hp - damage.getOrThrow()) to it.second)
            }
        }
    }
}

data class PokemonInBattle(
    var hp: Int,
    val pokemon: Pokemon
)
