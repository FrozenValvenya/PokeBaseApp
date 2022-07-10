package ru.frozenpriest.pokebase.domain

import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Pokemon
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.model.Token
import ru.frozenpriest.pokebase.domain.pokemon.PokemonData

interface RemoteRepository {
    suspend fun login(login: String, password: String): Result<Token>
    suspend fun register(login: String, password: String): Result<Token>
    suspend fun getOwnedPokemon(): Result<List<PokemonShort>>
    suspend fun getMoves(speciesId: String): Result<List<Move>>
    suspend fun getPokemonDetails(pokemonId: String): Result<Pokemon>
    suspend fun getSpecies(): Result<List<SpeciesShort>>
    suspend fun submitNewPokemon(pokemonData: PokemonData): Result<String>
    suspend fun addMove(pokemonId: String, moveId: String): Result<String>
    suspend fun removeMove(pokemonId: String, moveId: String): Result<String>
    suspend fun getDamage(attackerId: Int, defenderId: Int, moveId: Int): Result<Int>
}
