package ru.frozenpriest.pokebase.presentation.screens.pokemon.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import javax.inject.Inject

@Suppress("UnusedPrivateMember", "LongMethod")
class PokemonBattleViewModel @Inject constructor(
    //
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
        }
    }

    fun calculateDamage(move: Move) {
        val damage = 5
        pokemons.value?.let {
            if (turn.value == true) {
                _pokemons.postValue(it.first to it.second.copy(hp = it.second.hp - damage))
            } else {
                _pokemons.postValue(it.first.copy(hp = it.first.hp - damage) to it.second)
            }
        }
    }
}

data class PokemonInBattle(
    var hp: Int,
    val pokemon: Pokemon
)
