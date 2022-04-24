package ru.frozenpriest.pokebase.domain.model

data class Pokemon(
    val name: String,
    val level: Int,
    val species: Species,
    val height: Int,
    val weight: Float,
    val image: String
)

data class Species(
    val name: String,
    val hp: Stat,
    val attack: Stat,
    val defence: Stat,
    val spAttack: Stat,
    val spDefence: Stat,
    val speed: Stat,
    val types: List<String>
)

fun Species.getStats(): List<Stat> {
    return listOf(hp, attack, defence, spAttack, spDefence, speed)
}
