package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonMoveRequest(
    val pokemonId: Int,
    val moveId: Int
)
