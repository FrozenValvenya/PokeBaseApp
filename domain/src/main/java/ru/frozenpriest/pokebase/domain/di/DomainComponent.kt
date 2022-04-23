package ru.frozenpriest.pokebase.domain.di

import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [DomainFeatureDependencies::class], modules = [DomainModule::class])
@Singleton
internal interface DomainComponent : DomainFeatureApi {

    companion object {
        fun initAndGet(domainDependencies: DomainFeatureDependencies): DomainComponent {
            return DaggerDomainComponent.builder()
                .domainFeatureDependencies(domainDependencies)
                .build()
        }
    }
}
