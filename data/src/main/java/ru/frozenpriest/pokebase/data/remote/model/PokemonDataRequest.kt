package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDataRequest(
    val name: String,
    val level: Int,
    val speciesId: String
)
