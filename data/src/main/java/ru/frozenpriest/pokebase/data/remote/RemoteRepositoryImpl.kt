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
import ru.frozenpriest.pokebase.data.remote.model.toMove
import ru.frozenpriest.pokebase.data.remote.model.toPokemon
import ru.frozenpriest.pokebase.data.remote.model.toPokemonShort
import ru.frozenpriest.pokebase.data.remote.model.toShort
import ru.frozenpriest.pokebase.data.remote.model.toToken
import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.model.Token
import ru.frozenpriest.pokebase.domain.pokemon.PokemonData
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : RemoteRepository {
    override suspend fun login(login: String, password: String): Result<Token> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.LOGIN_URL)

                    contentType(ContentType.Application.Json)
                    setBody(Credentials(Username(login), Password(password)))
                }.body<TokenResponse>().toToken()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun register(login: String, password: String): Result<Token> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.REGISTER_URL)
                    contentType(ContentType.Application.Json)
                    setBody(Credentials(Username(login), Password(password)))
                }.body<TokenResponse>().toToken()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getOwnedPokemon(): Result<List<PokemonShort>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.OWNED_POKEMON)
                    contentType(ContentType.Application.Json)
                }.body<List<PokemonShortResponse>>().map { it.toPokemonShort() }
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getMoves(speciesId: String): Result<List<Move>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.getMovesRoute(speciesId))
                    contentType(ContentType.Application.Json)
                }.body<List<MoveResponse>>().map { it.toMove() }
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetails(pokemonId: String): Result<Pokemon> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.getPokemonDetailsRoute(pokemonId))
                    contentType(ContentType.Application.Json)
                }.body<PokemonResponse>().toPokemon()
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun getSpecies(): Result<List<SpeciesShort>> {
        return try {
            Result.success(
                client.get {
                    url(HttpRoutes.SPECIES_URL)
                    contentType(ContentType.Application.Json)
                }.body<List<SpeciesShortResponse>>().map { it.toShort() }
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }

    override suspend fun submitNewPokemon(pokemonData: PokemonData): Result<String> {
        return try {
            Result.success(
                client.put {
                    url(HttpRoutes.ADD_POKEMON)

                    contentType(ContentType.Application.Json)
                    setBody(
                        PokemonDataRequest(
                            pokemonData.name,
                            pokemonData.level,
                            pokemonData.species.speciesId.toInt()
                        )
                    )
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

    override suspend fun getDamage(attackerId: Int, defenderId: Int, moveId: Int): Result<Int> {
        return try {
            Result.success(
                client.post {
                    url(HttpRoutes.DAMAGE)

                    contentType(ContentType.Application.Json)
                    setBody(DamageRequest(attackerId, defenderId, moveId))
                }.body<DamageResponse>().damage
            )
        } catch (e: ResponseException) {
            Result.failure(e)
        }
    }
}
