package ru.frozenpriest.pokebase.di

import dagger.Component
import javax.inject.Singleton

@Component(dependencies = [AppFeatureDependencies::class], modules = [AppModule::class])
@Singleton
internal interface AppComponent : AppFeatureApi {

    companion object {
        fun initAndGet(appDependencies: AppFeatureDependencies): AppComponent {
            return DaggerAppComponent.builder()
                .appFeatureDependencies(appDependencies)
                .build()
        }
    }
}
