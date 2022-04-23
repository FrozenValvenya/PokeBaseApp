package ru.frozenpriest.pokebase.di

import ru.frozenpriest.pokebase.injector.BaseFeatureAPI
import ru.frozenpriest.pokebase.injector.BaseFeatureDependencies
import ru.frozenpriest.pokebase.injector.ComponentHolder
import ru.frozenpriest.pokebase.injector.ComponentHolderDelegate

object AppComponentHolder : ComponentHolder<AppFeatureApi, AppFeatureDependencies> {
    private val componentHolderDelegate = ComponentHolderDelegate<
        AppFeatureApi,
        AppFeatureDependencies,
        AppComponent> { dependencies: AppFeatureDependencies ->
        AppComponent.initAndGet(dependencies)
    }

    internal fun getComponent(): AppComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider: (() -> AppFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): AppFeatureApi = componentHolderDelegate.get()
}

interface AppFeatureDependencies : BaseFeatureDependencies

interface AppFeatureApi : BaseFeatureAPI
