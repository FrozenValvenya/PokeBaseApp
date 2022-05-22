package ru.frozenpriest.pokebase.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class Username(
    val username: String
)

@Serializable
@JvmInline
value class Password(
    val password: String
)

@Serializable
data class Credentials(
    val username: Username,
    val password: Password
)
