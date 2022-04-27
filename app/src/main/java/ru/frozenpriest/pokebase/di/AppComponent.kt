package ru.frozenpriest.pokebase.di

import dagger.Component
import javax.inject.Singleton

@Component(
    dependencies = [AppFeatureDependencies::class],
    modules = [AppModule::class, ViewModelModule::class]
)
@Singleton
internal interface AppComponent : AppFeatureApi {
    fun getFactory(): ViewModelFactory

    companion object {
        fun initAndGet(appDependencies: AppFeatureDependencies): AppComponent {
            return DaggerAppComponent.builder()
                .appFeatureDependencies(appDependencies)
                .build()
        }
    }
}
