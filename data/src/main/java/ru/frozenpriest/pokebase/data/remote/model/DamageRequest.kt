package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class DamageRequest(
    val attackerId: Int,
    val defenderId: Int,
    val moveId: Int
)

@Serializable
data class DamageResponse(
    val damage: Int
)
