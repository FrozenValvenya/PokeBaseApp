package ru.frozenpriest.pokebase.domain.di

import ru.frozenpriest.pokebase.data.remote.RemoteRepository
import ru.frozenpriest.pokebase.domain.login.LoginRegisterUseCase
import ru.frozenpriest.pokebase.injector.BaseFeatureAPI
import ru.frozenpriest.pokebase.injector.BaseFeatureDependencies
import ru.frozenpriest.pokebase.injector.ComponentHolder
import ru.frozenpriest.pokebase.injector.ComponentHolderDelegate

object DomainComponentHolder : ComponentHolder<DomainFeatureApi, DomainFeatureDependencies> {
    private val componentHolderDelegate = ComponentHolderDelegate<
        DomainFeatureApi,
        DomainFeatureDependencies,
        DomainComponent> { dependencies: DomainFeatureDependencies ->
        DomainComponent.initAndGet(dependencies)
    }

    internal fun getComponent(): DomainComponent = componentHolderDelegate.getComponentImpl()

    override var dependencyProvider: (() -> DomainFeatureDependencies)? by componentHolderDelegate::dependencyProvider

    override fun get(): DomainFeatureApi = componentHolderDelegate.get()
}

interface DomainFeatureDependencies : BaseFeatureDependencies {
    val remoteRepository: RemoteRepository
}

interface DomainFeatureApi : BaseFeatureAPI {
    val loginRegisterUseCase: LoginRegisterUseCase
}
