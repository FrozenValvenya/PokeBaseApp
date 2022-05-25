package ru.frozenpriest.pokebase.domain.login

import android.accounts.NetworkErrorException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import javax.inject.Inject

interface LoginRegisterUseCase {
    suspend fun login(login: String, password: String): Result<String>
    suspend fun register(login: String, password: String): Result<String>
}

class LoginRegisterUseCaseImpl @Inject constructor(
    private val pokeBaseRepository: RemoteRepository
) : LoginRegisterUseCase {
    override suspend fun login(login: String, password: String): Result<String> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                pokeBaseRepository.login(login, password).map { it.token }
            } catch (e: NetworkErrorException) {
                Result.failure(e)
            }
        }

    override suspend fun register(login: String, password: String): Result<String> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                pokeBaseRepository.register(login, password).map { it.token }
            } catch (e: NetworkErrorException) {
                Result.failure(e)
            }
        }
}
