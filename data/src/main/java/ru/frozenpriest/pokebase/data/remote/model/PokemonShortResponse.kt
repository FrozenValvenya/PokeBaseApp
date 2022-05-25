package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PokemonShortResponse(
    val id: String,
    val name: String,
    val species: SpeciesShort,
    val level: Int
)

@Serializable
data class SpeciesShort(
    val speciesId: Int,
    val name: String,
    val primaryType: String,
    val secondaryType: String?,
    val image: String
)
