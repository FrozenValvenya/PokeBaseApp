package ru.frozenpriest.pokebase.data.local

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import ru.frozenpriest.pokebase.domain.DataStoreRepository
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("poke_datastore")

class DataStoreRepositoryImpl @Inject constructor(
    application: Application
) : DataStoreRepository {
    private val dataStore = application.applicationContext.dataStore

    companion object {
        val bearerToken = stringPreferencesKey("boardingNeeded")
    }

    override suspend fun setBearerToken(token: String) {
        dataStore.edit { prefs ->
            prefs[bearerToken] = token
        }
    }

    override suspend fun getBearerToken(): String {
        return dataStore.data.first()[bearerToken] ?: ""
    }
}
