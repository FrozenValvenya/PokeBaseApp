package ru.frozenpriest.pokebase.di

import dagger.Component
import ru.frozenpriest.pokebase.data.local.DataStoreRepository
import javax.inject.Singleton

@Component(
    dependencies = [AppFeatureDependencies::class],
    modules = [AppModule::class, ViewModelModule::class]
)
@Singleton
internal interface AppComponent : AppFeatureApi {
    fun getFactory(): ViewModelFactory
    fun getDataStoreRepository(): DataStoreRepository

    companion object {
        fun initAndGet(appDependencies: AppFeatureDependencies): AppComponent {
            return DaggerAppComponent.builder()
                .appFeatureDependencies(appDependencies)
                .build()
        }
    }
}
