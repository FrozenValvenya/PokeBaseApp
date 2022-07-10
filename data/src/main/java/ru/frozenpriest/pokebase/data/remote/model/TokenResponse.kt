package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.frozenpriest.pokebase.domain.model.Token

@Serializable
data class TokenResponse(
    @SerialName("JWT")
    val token: String
)

fun TokenResponse.toToken(): Token = Token(token)
