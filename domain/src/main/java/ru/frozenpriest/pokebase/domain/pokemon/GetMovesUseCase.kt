package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Species
import javax.inject.Inject

interface GetMovesUseCase {
    suspend fun getMoves(pokemonSpecies: Species): Result<List<Move>>
    suspend fun addMove(pokemonId: String, moveId: String)
    suspend fun removeMove(pokemonId: String, moveId: String)
}

class GetMovesUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetMovesUseCase {
    override suspend fun getMoves(pokemonSpecies: Species): Result<List<Move>> {
        return pokeBaseRepository.getMoves(pokemonSpecies.id)
    }

    override suspend fun addMove(pokemonId: String, moveId: String) {
        pokeBaseRepository.addMove(pokemonId, moveId)
    }

    override suspend fun removeMove(pokemonId: String, moveId: String) {
        pokeBaseRepository.removeMove(pokemonId, moveId)
    }
}
