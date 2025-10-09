package org.fahimdev.cmpboilerplate

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

const val DATA_STORE_NAME = "data_store.preferences_pb"

fun createDataStore(producePath: () -> String): DataStore<Preferences>{
    return PreferenceDataStoreFactory.createWithPath(
        produceFile = {producePath().toPath()}
    )
}