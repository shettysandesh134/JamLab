package com.sandeshshetty.jamlab.business.data.preferences.implementation

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.sandeshshetty.jamlab.business.data.preferences.abstraction.DataStoreRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


private const val PREFERENCES_NAME = "medical_preferences"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreRepositoryImpl @Inject
constructor(
    private val context: Context
): DataStoreRepository{

    override suspend fun putString(key: String, value: String) {
        val preferenceKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun putInt(key: String, value: Int) {
        val preferenceKey = intPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun putBoolen(key: String, value: Boolean) {
        val preferenceKey = booleanPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences[preferenceKey] = value
        }
    }

    override suspend fun getString(key: String): String? {
        val preferenceKey = stringPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferenceKey]
    }

    override suspend fun getInt(key: String): Int? {
        val preferenceKey = intPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferenceKey]
    }

    override suspend fun getBoolean(key: String): Boolean? {
        val preferenceKey = booleanPreferencesKey(key)
        val preferences = context.dataStore.data.first()
        return preferences[preferenceKey]
    }

}