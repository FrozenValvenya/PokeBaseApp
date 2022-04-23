package ru.frozenpriest.pokebase.data.di

import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [DataFeatureDependencies::class], modules = [DataModule::class])
@Singleton
internal interface DataComponent : DataFeatureApi {

    companion object {
        fun initAndGet(dataDependencies: DataFeatureDependencies): DataComponent {
            return DaggerDataComponent.builder()
                .dataFeatureDependencies(dataDependencies)
                .build()
        }
    }
}
