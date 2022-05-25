package ru.frozenpriest.pokebase.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.frozenpriest.pokebase.data.remote.model.Credentials
import ru.frozenpriest.pokebase.data.remote.model.MoveResponse
import ru.frozenpriest.pokebase.data.remote.model.Password
import ru.frozenpriest.pokebase.data.remote.model.PokemonResponse
import ru.frozenpriest.pokebase.data.remote.model.PokemonShortResponse
import ru.frozenpriest.pokebase.data.remote.model.TokenResponse
import ru.frozenpriest.pokebase.data.remote.model.Username
import javax.inject.Inject

interface RemoteRepository {
    suspend fun login(login: String, password: String): Result<TokenResponse>
    suspend fun register(login: String, password: String): Result<TokenResponse>
    suspend fun getOwnedPokemon(): Result<List<PokemonShortResponse>>
    suspend fun getMoves(speciesId: String): Result<List<MoveResponse>>
    suspend fun getPokemonDetails(pokemonId: String): Result<PokemonResponse>
}

class RemoteRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : RemoteRepository {
    override suspend fun login(login: String, password: String): Result<TokenResponse> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.LOGIN_URL)

                    contentType(ContentType.Application.Json)
                    setBody(Credentials(Username(login), Password(password)))
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun register(login: String, password: String): Result<TokenResponse> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.REGISTER_URL)
                    contentType(ContentType.Application.Json)
                    setBody(Credentials(Username(login), Password(password)))
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getOwnedPokemon(): Result<List<PokemonShortResponse>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.OWNED_POKEMON)
                    contentType(ContentType.Application.Json)
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getMoves(speciesId: String): Result<List<MoveResponse>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.getMovesRoute(speciesId))
                    contentType(ContentType.Application.Json)
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetails(pokemonId: String): Result<PokemonResponse> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.getPokemonDetailsRoute(pokemonId))
                    contentType(ContentType.Application.Json)
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }
}
