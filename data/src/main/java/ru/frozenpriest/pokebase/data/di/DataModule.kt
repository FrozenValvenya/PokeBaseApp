package ru.frozenpriest.pokebase.data.di

import android.app.Application
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.frozenpriest.pokebase.data.local.DataStoreRepositoryImpl
import ru.frozenpriest.pokebase.data.remote.RemoteRepositoryImpl
import ru.frozenpriest.pokebase.domain.DataStoreRepository
import ru.frozenpriest.pokebase.domain.RemoteRepository

@Module
internal class DataModule {

    @Provides
    fun provideRemoteRepository(httpClient: HttpClient): RemoteRepository =
        RemoteRepositoryImpl(httpClient)

    @Provides
    fun provideDataStoreRepository(application: Application): DataStoreRepository =
        DataStoreRepositoryImpl(application)

    @Provides
    fun provideHttpClient(dataStoreRepository: DataStoreRepository): HttpClient {
        return HttpClient(Android) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    },
                    contentType = ContentType.Application.Json
                )
            }
            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(dataStoreRepository.getBearerToken(), "")
                    }
                }
            }
        }
    }
}
