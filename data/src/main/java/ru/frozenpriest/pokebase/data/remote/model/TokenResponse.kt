package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TokenResponse(
    @SerialName("JWT")
    val token: String
)
