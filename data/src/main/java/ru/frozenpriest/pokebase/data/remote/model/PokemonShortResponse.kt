package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonShortResponse(
    val pokemonId: String,
    val nickname: String,
    val species: SpeciesShortResponse,
    val level: Int
)

@Serializable
data class SpeciesShortResponse(
    val speciesId: String,
    val name: String,
    val primaryType: String,
    val secondaryType: String?,
    val image: String
)
