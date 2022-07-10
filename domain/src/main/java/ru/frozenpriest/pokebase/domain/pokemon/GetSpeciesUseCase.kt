package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.domain.model.SpeciesShort
import javax.inject.Inject

interface GetSpeciesUseCase {
    suspend fun getSpecies(): Result<List<SpeciesShort>>
}

class GetSpeciesUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetSpeciesUseCase {
    override suspend fun getSpecies(): Result<List<SpeciesShort>> {
        return pokeBaseRepository.getSpecies()
    }
}
