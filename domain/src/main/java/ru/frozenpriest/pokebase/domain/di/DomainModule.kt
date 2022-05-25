package ru.frozenpriest.pokebase.domain.di

import dagger.Module
import dagger.Provides
import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCase
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCaseImpl
import ru.frozenpriest.pokebase.domain.pokemon.AddPokemonUseCase
import ru.frozenpriest.pokebase.domain.pokemon.AddPokemonUseCaseImpl
import ru.frozenpriest.pokebase.domain.pokemon.GetMovesUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetMovesUseCaseImpl
import ru.frozenpriest.pokebase.domain.pokemon.GetOwnedPokemonShortUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetOwnedPokemonShortUseCaseImpl
import ru.frozenpriest.pokebase.domain.pokemon.GetPokemonDetailsUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetPokemonDetailsUseCaseImpl
import ru.frozenpriest.pokebase.domain.pokemon.GetSpeciesUseCase
import ru.frozenpriest.pokebase.domain.pokemon.GetSpeciesUseCaseImpl

@Module
internal class DomainModule {
    @Provides
    fun provideLoginRegisterUseCase(repository: RemoteRepository): LoginRegisterUseCase =
        LoginRegisterUseCaseImpl(repository)

    @Provides
    fun provideGetPokemonShort(repository: RemoteRepository): GetOwnedPokemonShortUseCase =
        GetOwnedPokemonShortUseCaseImpl(repository)

    @Provides
    fun provideGetMovesUseCase(repository: RemoteRepository): GetMovesUseCase =
        GetMovesUseCaseImpl(repository)

    @Provides
    fun provideGetPokemonDetailsUseCase(repository: RemoteRepository): GetPokemonDetailsUseCase =
        GetPokemonDetailsUseCaseImpl(repository)

    @Provides
    fun provideGetSpeciesUseCase(repository: RemoteRepository): GetSpeciesUseCase =
        GetSpeciesUseCaseImpl(repository)

    @Provides
    fun provideAddPokemonUseCase(repository: RemoteRepository): AddPokemonUseCase =
        AddPokemonUseCaseImpl(repository)
}
