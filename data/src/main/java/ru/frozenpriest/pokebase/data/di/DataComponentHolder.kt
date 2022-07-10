package ru.frozenpriest.pokebase.data.di

import android.app.Application
import ru.frozenpriest.pokebase.domain.DataStoreRepository
import ru.frozenpriest.pokebase.domain.RemoteRepository
import ru.frozenpriest.pokebase.injector.BaseFeatureAPI
import ru.frozenpriest.pokebase.injector.BaseFeatureDependencies
import ru.frozenpriest.pokebase.injector.ComponentHolder
import ru.frozenpriest.pokebase.injector.ComponentHolderDelegate

object DataComponentHolder : ComponentHolder<DataFeatureApi, DataFeatureDependencies> {
    private val componentHolderDelegate = ComponentHolderDelegate<
        DataFeatureApi,
        DataFeatureDependencies,
        DataComponent> { dependencies: DataFeatureDependencies ->
        DataComponent.initAndGet(dependencies)
    }

    internal fun getComponent(): DataComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider: (() -> DataFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): DataFeatureApi = componentHolderDelegate.get()
}

interface DataFeatureDependencies : BaseFeatureDependencies {
    val application: Application
}

interface DataFeatureApi : BaseFeatureAPI {
    val remoteRepository: RemoteRepository
    val dataStoreRepository: DataStoreRepository
}
