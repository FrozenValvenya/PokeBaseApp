package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class SpeciesResponse(
    val speciesId: Int,
    val name: String,
    val primaryType: String,
    val secondaryType: String?,
    val evolutions: List<SpeciesResponse>,
    val weight: Float,
    val height: Float,
    val baseStats: StatsResponse,
    val movePool: List<MoveResponse>,
    val image: String
)

@Serializable
data class StatsResponse(
    val hp: Int,
    val atk: Int,
    val def: Int,
    val spa: Int,
    val spd: Int,
    val spe: Int
)
