package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MoveResponse(
    val moveId: Int,
    val name: String,
    val type: String,
    val category: String,
    val power: Int?,
    val accuracy: Int?,
    val pp: Int,
    val description: String
)
