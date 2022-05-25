package ru.frozenpriest.pokebase.data.remote.model

data class PokemonShortResponse(
    val id: String,
    val name: String,
    val speciesName: String,
    val imageUrl: String,
    val types: List<String>,
)
