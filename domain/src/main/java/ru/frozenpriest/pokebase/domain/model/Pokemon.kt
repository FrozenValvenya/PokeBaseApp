package ru.frozenpriest.pokebase.domain.model

import ru.frozenpriest.pokebase.data.remote.model.MoveResponse

data class Pokemon(
    val id: String,
    val name: String,
    val level: Int,
    val species: Species,
    val moves: List<Move>
)

data class Species(
    val id: String,
    val name: String,
    val image: String,
    val height: Int,
    val weight: Float,
    val hp: Stat,
    val attack: Stat,
    val defence: Stat,
    val spAttack: Stat,
    val spDefence: Stat,
    val speed: Stat,
    val types: List<Type>,
    val possibleEvolutions: List<Species>
)

fun Species.getStats(): List<Stat> {
    return listOf(hp, attack, defence, spAttack, spDefence, speed)
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

data class Move(
    val name: String,
    val type: Type,
    val category: Category,
    val power: Int?,
    val accuracy: Float?,
    val pp: Int,
)

fun MoveResponse.toMove(): Move {
    return Move(
        name = name,
        type = Type.valueOf(type),
        category = Category.valueOf(category),
        power = power,
        accuracy = accuracy?.toFloat(),
        pp = pp
    )
}
