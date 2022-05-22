package ru.frozenpriest.pokebase.presentation.screens.pokemon.battle

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.frozenpriest.pokebase.domain.model.Category
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Type
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
            _pokemons.postValue(
                PokemonInBattle(
                    24,
                    Pokemon(
                        id = "r3r32r3r",
                        name = "Poke333",
                        species = Species(
                            name = "Bulbasaur333",
                            hp = Stat.makeHP(85),
                            attack = Stat.makeAttack(10),
                            defence = Stat.makeDefence(20),
                            spAttack = Stat.makeSpAttack(20),
                            spDefence = Stat.makeSpDefence(20),
                            speed = Stat.makeSpeed(20),
                            types = listOf(Type.Grass, Type.Poison),
                            possibleEvolutions = listOf(),
                            height = 70,
                            weight = 6.9f,
                            image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                        ),
                        level = 5,

                        moves = listOf(
                            Move("LUL", Type.Poison, Category.Status, null, 1.0f, 999),
                            Move("LUL2", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                            Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33)
                        )
                    )
                )
                    to
                        PokemonInBattle(
                            99,
                            Pokemon(
                                id = "r3r32r3r",
                                name = "Poke333",
                                species = Species(
                                    name = "Bulbasaur333",
                                    hp = Stat.makeHP(85),
                                    attack = Stat.makeAttack(10),
                                    defence = Stat.makeDefence(20),
                                    spAttack = Stat.makeSpAttack(20),
                                    spDefence = Stat.makeSpDefence(20),
                                    speed = Stat.makeSpeed(20),
                                    types = listOf(Type.Grass, Type.Poison),
                                    possibleEvolutions = listOf(),
                                    height = 70,
                                    weight = 6.9f,
                                    image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                                ),
                                level = 5,

                                moves = listOf(
                                    Move(
                                        "LUL_other",
                                        Type.Poison,
                                        Category.Status,
                                        null,
                                        1.0f,
                                        999
                                    ),
                                    Move("LUL2_other", Type.Rock, Category.Physical, 8888, 0.0f, 1),
                                    Move("LUL3", Type.Dragon, Category.Special, 7777, 0.35f, 33)
                                )
                            )
                        )
            )
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
