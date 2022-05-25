package ru.frozenpriest.pokebase.domain.model

import ru.frozenpriest.pokebase.data.remote.model.PokemonResponse
import ru.frozenpriest.pokebase.data.remote.model.SpeciesResponse
import ru.frozenpriest.pokebase.data.remote.model.StatsResponse

data class Pokemon(
    val id: String,
    val name: String,
    val level: Int,
    val species: Species,
    val moves: List<Move>
)

fun PokemonResponse.toPokemon(): Pokemon {
    return Pokemon(
        this.pokemonId.toString(),
        this.nickname,
        this.level,
        this.species.toSpecies(),
        this.moves.map { it.toMove() }
    )
}

data class Species(
    val id: String,
    val name: String,
    val types: List<Type>,
    val evolutions: List<Species>,
    val weight: Float,
    val height: Float,
    val baseStats: Stats,
    val movePool: List<Move>,
    val image: String
)

fun SpeciesResponse.toSpecies(): Species {
    return Species(
        this.speciesId.toString(),
        this.name,
        listOfNotNull(primaryType, secondaryType).map { Type.valueOf(it) },
        this.evolutions.map { it.toSpecies() },
        this.weight,
        this.height,
        this.baseStats.toStats(),
        this.movePool.map { it.toMove() },
        this.image
    )
}

data class Stats(
    val hp: Stat,
    val atk: Stat,
    val def: Stat,
    val spa: Stat,
    val spd: Stat,
    val spe: Stat
)

fun StatsResponse.toStats(): Stats {
    return Stats(
        Stat.makeHP(this.hp),
        Stat.makeAttack(atk),
        Stat.makeDefence(def),
        Stat.makeSpAttack(spa),
        Stat.makeSpDefence(spd),
        Stat.makeSpeed(spe)
    )
}

fun Species.getStats(): List<Stat> {
    return listOf(
        baseStats.hp,
        baseStats.atk,
        baseStats.def,
        baseStats.spa,
        baseStats.spd,
        baseStats.spe
    )
}

enum class Type(val typeName: String) {
    Normal("Normal"),
    Fighting("Fighting"),
    Flying("Flying"),
    Poison("Poison"),
    Ground("Ground"),
    Rock("Rock"),
    Bug("Bug"),
    Ghost("Ghost"),
    Steel("Steel"),
    Fire("Fire"),
    Water("Water"),
    Grass("Grass"),
    Electric("Electric"),
    Psychic("Psychic"),
    Ice("Ice"),
    Dragon("Dragon"),
    Dark("Dark"),
    Fairy("Fairy"),
    Unknown("???"),
}

enum class Category {
    Physical, Status, Special
}
