package ru.frozenpriest.pokebase.domain.model

data class PokemonShort(
    val id: String,
    val name: String,
    val speciesName: String,
    val imageUrl: String,
    val types: List<Type>,
)
