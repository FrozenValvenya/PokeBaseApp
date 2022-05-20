package ru.frozenpriest.pokebase.presentation.screens.pokemon.owned

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.Stat
import ru.frozenpriest.pokebase.domain.model.Type
import javax.inject.Inject

class OwnedPokemonsViewModel @Inject constructor() : ViewModel() {
    val pokemonFlow: Flow<List<Pokemon>> = flow {
        emit(
            listOf(
                Pokemon(
                    id = "342343432",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        height = 70,
                        weight = 6.9f,
                        types = listOf(Type.Grass, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,

                    moves = listOf()
                ),
                Pokemon(
                    id = "342343432",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
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

                    moves = listOf()
                ),
                Pokemon(
                    id = "342343432",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Grass, Type.Poison),
                        height = 70,
                        weight = 6.9f,
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "342343432",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
                        hp = Stat.makeHP(85),
                        height = 70,
                        weight = 6.9f,
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Grass, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "342343432",
                    name = "Poke",
                    species = Species(
                        name = "Bulbasaur",
                        height = 70,
                        weight = 6.9f,
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Grass, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        height = 70,
                        weight = 6.9f,
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Fire, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Fire, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        height = 70,
                        weight = 6.9f,
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Fire, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Electric, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "ERDEF",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Dark, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Fire, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Electric, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "ERDEF",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        speed = Stat.makeSpeed(20),
                        height = 70,
                        weight = 6.9f,
                        types = listOf(Type.Dark, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Fire, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "Red",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Electric, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
                Pokemon(
                    id = "434334",
                    name = "ERDEF",
                    species = Species(
                        name = "LULFORGOT",
                        hp = Stat.makeHP(85),
                        attack = Stat.makeAttack(10),
                        defence = Stat.makeDefence(20),
                        spAttack = Stat.makeSpAttack(20),
                        spDefence = Stat.makeSpDefence(20),
                        height = 70,
                        weight = 6.9f,
                        speed = Stat.makeSpeed(20),
                        types = listOf(Type.Dark, Type.Poison),
                        possibleEvolutions = listOf(),
                        image = "https://archives.bulbagarden.net/media/upload/2/21/001Bulbasaur.png",
                    ),
                    level = 5,
                    moves = listOf()
                ),
            )
        )
    }
}
