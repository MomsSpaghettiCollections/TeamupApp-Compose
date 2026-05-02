package com.ups.android.apps.storage.datastore.di


import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.ups.android.apps.storage.datastore.UserDataSource
import com.ups.android.apps.storage.datastore.UserPreferencesSerializer
import com.ups.android.apps.storage.datastore.data.UserPreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val userPreferenceModule = module {
    single {
        UserPreferencesSerializer()
    }
    single {
        UserDataSource(datastore = get())
    }
    single<DataStore<UserPreferences>> {
        DataStoreFactory.create(
            serializer = get<UserPreferencesSerializer>()
        ) {
            androidContext().dataStoreFile("user_preferences.pb")
        }
    }
}