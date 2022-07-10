package ru.frozenpriest.pokebase.domain.pokemon

import ru.frozenpriest.pokebase.domain.RemoteRepository
import javax.inject.Inject

interface GetDamageUseCase {
    suspend fun calculateDamage(
        attackerId: Int,
        defenderId: Int,
        moveId: Int
    ): Result<Int>
}

class GetDamageUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : GetDamageUseCase {
    override suspend fun calculateDamage(
        attackerId: Int,
        defenderId: Int,
        moveId: Int
    ): Result<Int> {
        return pokeBaseRepository.getDamage(attackerId, defenderId, moveId)
    }
}
