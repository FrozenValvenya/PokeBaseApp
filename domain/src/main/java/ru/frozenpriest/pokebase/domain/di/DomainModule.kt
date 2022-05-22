package ru.frozenpriest.pokebase.domain.di

import dagger.Module
import dagger.Provides
import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCase
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCaseImpl

@Module
internal class DomainModule {
    @Provides
    fun provideLoginRegisterUseCase(repository: RemoteRepository): LoginRegisterUseCase =
        LoginRegisterUseCaseImpl(repository)
}
