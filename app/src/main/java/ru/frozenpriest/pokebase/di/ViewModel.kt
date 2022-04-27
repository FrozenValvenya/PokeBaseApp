package ru.frozenpriest.pokebase.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

/**
 * It uses LocalViewModelStoreOwner,
 * which means the owner may be Activity, Fragment or NavBackStackEntry
 * For our purposes we are going to use NavBackStackEntry
 */
@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    factory: ViewModelFactory
): T =
    androidx.lifecycle.viewmodel.compose.viewModel(
        modelClass = T::class.java,
        key = key,
        factory = factory
    )
