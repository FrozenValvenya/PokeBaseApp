package ru.frozenpriest.pokebase.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import ru.frozenpriest.pokebase.data.remote.model.Credentials
import ru.frozenpriest.pokebase.data.remote.model.DamageRequest
import ru.frozenpriest.pokebase.data.remote.model.DamageResponse
import ru.frozenpriest.pokebase.data.remote.model.MoveResponse
import ru.frozenpriest.pokebase.data.remote.model.Password
import ru.frozenpriest.pokebase.data.remote.model.PokemonDataRequest
import ru.frozenpriest.pokebase.data.remote.model.PokemonIdResponse
import ru.frozenpriest.pokebase.data.remote.model.PokemonMoveRequest
import ru.frozenpriest.pokebase.data.remote.model.PokemonResponse
import ru.frozenpriest.pokebase.data.remote.model.PokemonShortResponse
import ru.frozenpriest.pokebase.data.remote.model.SpeciesShortResponse
import ru.frozenpriest.pokebase.data.remote.model.TokenResponse
import ru.frozenpriest.pokebase.data.remote.model.Username
import javax.inject.Inject

interface RemoteRepository {
    suspend fun login(login: String, password: String): Result<TokenResponse>
    suspend fun register(login: String, password: String): Result<TokenResponse>
    suspend fun getOwnedPokemon(): Result<List<PokemonShortResponse>>
    suspend fun getMoves(speciesId: String): Result<List<MoveResponse>>
    suspend fun getPokemonDetails(pokemonId: String): Result<PokemonResponse>
    suspend fun getSpecies(): Result<List<SpeciesShortResponse>>
    suspend fun submitNewPokemon(pokemonData: PokemonDataRequest): Result<String>
    suspend fun addMove(pokemonId: String, moveId: String): Result<String>
    suspend fun removeMove(pokemonId: String, moveId: String): Result<String>
    suspend fun getDamage(damageRequest: DamageRequest): Result<Int>
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

    override suspend fun getSpecies(): Result<List<SpeciesShortResponse>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.SPECIES_URL)
                    contentType(ContentType.Application.Json)
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun submitNewPokemon(pokemonData: PokemonDataRequest): Result<String> {
        return try {
            Result.success(
                client.put {
                    url(HttpRoutes.ADD_POKEMON)

                    contentType(ContentType.Application.Json)
                    setBody(pokemonData)
                }.body<PokemonIdResponse>().pokemonId.toString()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun addMove(pokemonId: String, moveId: String): Result<String> {
        return try {
            Result.success(
                client.put {
                    url(HttpRoutes.ADD_MOVE)

                    contentType(ContentType.Application.Json)
                    setBody(PokemonMoveRequest(pokemonId.toInt(), moveId.toInt()))
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun removeMove(pokemonId: String, moveId: String): Result<String> {
        return try {
            Result.success(
                client.delete {
                    url(HttpRoutes.ADD_MOVE)

                    contentType(ContentType.Application.Json)
                    setBody(PokemonMoveRequest(pokemonId.toInt(), moveId.toInt()))
                }.body()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getDamage(damageRequest: DamageRequest): Result<Int> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.DAMAGE)

                    contentType(ContentType.Application.Json)
                    setBody(damageRequest)
                }.body<DamageResponse>().damage
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }
}
