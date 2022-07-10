package ru.frozenpriest.pokebase.domain.model

data class Pokemon(
    val id: String,
    val name: String,
    val level: Int,
    val species: Species,
    val moves: List<Move>,
    val stats: Stats,
)

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

data class SpeciesShort(
    val speciesId: String,
    val name: String,
    val types: List<Type>,
    val image: String
)

data class Stats(
    val hp: Stat,
    val atk: Stat,
    val def: Stat,
    val spa: Stat,
    val spd: Stat,
    val spe: Stat
)

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
