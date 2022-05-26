package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import ru.frozenpriest.pokebase.domain.model.toShort
import javax.inject.Inject

interface GetSpeciesUseCase {
    suspend fun getSpecies(): Result<List<SpeciesShort>>
}

class GetSpeciesUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetSpeciesUseCase {
    override suspend fun getSpecies(): Result<List<SpeciesShort>> {
        return pokeBaseRepository.getSpecies().map { list ->
            list.map { speciesResponse -> speciesResponse.toShort() }
        }
    }
}
