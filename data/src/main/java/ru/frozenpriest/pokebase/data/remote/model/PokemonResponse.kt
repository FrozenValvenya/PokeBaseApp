package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val pokemonId: Int,
    val nickname: String,
    val species: SpeciesResponse,
    val level: Int,
    val stats: StatsResponse,
    val moves: List<MoveResponse>
)
