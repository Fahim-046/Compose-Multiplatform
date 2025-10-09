package org.fahimdev.cmpboilerplate.core.managers

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreManager(private val dataStore: DataStore<Preferences>){
    suspend fun saveInt(key: String, value: Int){
        val prefKey = intPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    suspend fun getInt(key: String, default: Int = 0): Int{
        val prefKey = intPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[prefKey] ?: default
    }

    suspend fun saveString(key: String, value: String){
        val prefKey = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    suspend fun getString(key: String, default: String? = null): String?{
        val prefKey = stringPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[prefKey] ?: default
    }

    suspend fun saveBoolean(key: String, value: Boolean){
        val prefKey = booleanPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[prefKey] = value
        }
    }

    suspend fun getBoolean(key: String, default: Boolean = false): Boolean{
        val prefKey = booleanPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[prefKey] ?: default
    }

    fun getBooleanFlow(key: String, default: Boolean = false): Flow<Boolean> {
        val prefKey = booleanPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[prefKey] ?: default
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}