package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonShortResponse(
    val id: String,
    val name: String,
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
