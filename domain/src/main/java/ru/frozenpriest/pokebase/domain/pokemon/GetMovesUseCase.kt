package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.Move
import ru.frozenpriest.pokebase.domain.model.Species
import ru.frozenpriest.pokebase.domain.model.toMove
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
        return pokeBaseRepository.getMoves(pokemonSpecies.id).map { list ->
            list.map { movesResponse -> movesResponse.toMove() }
        }
    }

    override suspend fun addMove(pokemonId: String, moveId: String) {
        pokeBaseRepository.addMove(pokemonId, moveId)
    }

    override suspend fun removeMove(pokemonId: String, moveId: String) {
        pokeBaseRepository.removeMove(pokemonId, moveId)
    }
}
