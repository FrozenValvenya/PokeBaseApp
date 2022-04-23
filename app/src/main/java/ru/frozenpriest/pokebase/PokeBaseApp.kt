package ru.frozenpriest.pokebase

import android.app.Application
import ru.frozenpriest.pokebase.data.di.DataComponentHolder
import ru.frozenpriest.pokebase.data.di.DataFeatureApi
import ru.frozenpriest.pokebase.data.di.DataFeatureDependencies
import ru.frozenpriest.pokebase.di.AppComponentHolder
import ru.frozenpriest.pokebase.di.AppFeatureDependencies
import ru.frozenpriest.pokebase.domain.di.DomainComponentHolder
import ru.frozenpriest.pokebase.domain.di.DomainFeatureApi
import ru.frozenpriest.pokebase.domain.di.DomainFeatureDependencies
import ru.frozenpriest.pokebase.injector.BaseDependencyHolder
import ru.frozenpriest.pokebase.injector.BaseFeatureDependencies
import ru.frozenpriest.pokebase.injector.DependencyHolder0
import ru.frozenpriest.pokebase.injector.DependencyHolder1
import timber.log.Timber

@Suppress("MaxLineLength")
class PokeBaseApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        AppComponentHolder.dependencyProvider = provideAppDependencyProvider()
        DataComponentHolder.dependencyProvider = provideDataDependencyProvider()
        DomainComponentHolder.dependencyProvider = provideDomainDependencyProvider()
    }

    private fun provideAppDependencyProvider(): () -> AppFeatureDependencies = {
        class AppComponentDependencyHolder(
            override val block: (BaseDependencyHolder<AppFeatureDependencies>, DomainFeatureApi) -> AppFeatureDependencies
        ) : DependencyHolder1<DomainFeatureApi, AppFeatureDependencies>(
            api1 = DomainComponentHolder.get()
        )

        AppComponentDependencyHolder { dependencyHolder, domainApi ->
            object : AppFeatureDependencies {
                override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> =
                    dependencyHolder
            }
        }.dependencies
    }

    private fun provideDataDependencyProvider(): () -> DataFeatureDependencies = {
        class DataComponentDependencyHolder(
            override val block: (BaseDependencyHolder<DataFeatureDependencies>) -> DataFeatureDependencies
        ) : DependencyHolder0<DataFeatureDependencies>()

        DataComponentDependencyHolder { dependencyHolder ->
            object : DataFeatureDependencies {
                override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> =
                    dependencyHolder
                override val application = this@PokeBaseApp
            }
        }.dependencies
    }

    private fun provideDomainDependencyProvider(): () -> DomainFeatureDependencies = {
        class DomainComponentDependencyHolder(
            override val block: (BaseDependencyHolder<DomainFeatureDependencies>, DataFeatureApi) -> DomainFeatureDependencies
        ) : DependencyHolder1<DataFeatureApi, DomainFeatureDependencies>(
            api1 = DataComponentHolder.get()
        )

        DomainComponentDependencyHolder { dependencyHolder, dataFeaturesApi ->
            object : DomainFeatureDependencies {
                override val dependencyHolder: BaseDependencyHolder<out BaseFeatureDependencies> =
                    dependencyHolder
            }
        }.dependencies
    }
}
