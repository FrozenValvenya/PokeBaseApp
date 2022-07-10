package ru.frozenpriest.pokebase.domain

interface DataStoreRepository {
    suspend fun getBearerToken(): String
    suspend fun setBearerToken(token: String)
}
