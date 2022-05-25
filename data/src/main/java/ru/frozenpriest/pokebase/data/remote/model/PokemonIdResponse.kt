package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonIdResponse(
    val pokemonId: Int
)
