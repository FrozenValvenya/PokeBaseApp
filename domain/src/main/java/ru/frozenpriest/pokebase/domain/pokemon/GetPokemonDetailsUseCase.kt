package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.Pokemon
import javax.inject.Inject

interface GetPokemonDetailsUseCase {
    suspend fun getPokemonDetails(pokemonId: String): Result<Pokemon>
}

class GetPokemonDetailsUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetPokemonDetailsUseCase {
    override suspend fun getPokemonDetails(pokemonId: String): Result<Pokemon> {
        return pokeBaseRepository.getPokemonDetails(pokemonId)
    }
}
