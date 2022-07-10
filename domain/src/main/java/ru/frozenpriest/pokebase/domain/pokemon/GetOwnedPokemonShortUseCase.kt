package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.PokemonShort
import javax.inject.Inject

interface GetOwnedPokemonShortUseCase {
    suspend fun getPokemon(): Result<List<PokemonShort>>
}

class GetOwnedPokemonShortUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetOwnedPokemonShortUseCase {
    override suspend fun getPokemon(): Result<List<PokemonShort>> {
        return pokeBaseRepository.getOwnedPokemon()
    }
}
