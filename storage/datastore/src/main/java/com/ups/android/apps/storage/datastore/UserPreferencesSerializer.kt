package com.ups.android.apps.storage.datastore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.OutputStream
import androidx.datastore.core.Serializer
import com.ups.android.apps.storage.datastore.data.UserPreferences

class UserPreferencesSerializer :Serializer<UserPreferences> {

    override val defaultValue: UserPreferences =
        UserPreferences.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserPreferences =
        withContext(Dispatchers.IO) {
            runCatching {
                UserPreferences.parseFrom(input)
            }.getOrThrow()
        }


    override suspend fun writeTo(t: UserPreferences, output: OutputStream) {
        withContext(Dispatchers.IO) {
            t.writeTo(output)
        }
    }
}