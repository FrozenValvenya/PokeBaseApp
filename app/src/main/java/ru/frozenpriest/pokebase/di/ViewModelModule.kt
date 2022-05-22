package ru.frozenpriest.pokebase.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ru.frozenpriest.pokebase.presentation.screens.login.LoginRegisterViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.add.AddNewPokemonViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.battle.PokemonBattleViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.details.PokemonDetailsViewModel
import ru.frozenpriest.pokebase.presentation.screens.pokemon.owned.OwnedPokemonsViewModel
import javax.inject.Inject
import javax.inject.Provider
import kotlin.reflect.KClass

@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginRegisterViewModel::class)
    abstract fun loginRegisterViewModel(myViewModel: LoginRegisterViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailsViewModel::class)
    abstract fun pokemonDetailsViewModel(myViewModel: PokemonDetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OwnedPokemonsViewModel::class)
    abstract fun ownedPokemonsViewModel(myViewModel: OwnedPokemonsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AddNewPokemonViewModel::class)
    abstract fun addNewPokemonViewModel(myViewModel: AddNewPokemonViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonBattleViewModel::class)
    abstract fun pokemonBattleViewModel(myViewModel: PokemonBattleViewModel): ViewModel
}

class ViewModelFactory @Inject constructor(
    private val providersMap: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = providersMap[modelClass]
        val viewModel =
            provider?.get() ?: throw NotImplementedError("unsupported viewmodel type: $modelClass")

        return viewModel as T
    }
}
