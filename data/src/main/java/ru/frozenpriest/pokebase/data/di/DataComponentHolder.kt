package ru.frozenpriest.pokebase.data.di

import ru.frozenpriest.pokebase.module_injector.BaseFeatureAPI
import ru.frozenpriest.pokebase.module_injector.BaseFeatureDependencies
import ru.frozenpriest.pokebase.module_injector.ComponentHolder
import ru.frozenpriest.pokebase.module_injector.ComponentHolderDelegate

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

interface DataFeatureDependencies : BaseFeatureDependencies

interface DataFeatureApi : BaseFeatureAPI
