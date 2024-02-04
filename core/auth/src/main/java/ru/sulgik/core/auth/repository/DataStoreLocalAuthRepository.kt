package ru.sulgik.core.auth.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import ru.sulgik.core.auth.domain.AuthScope

class DataStoreLocalAuthRepository(
    private val context: Context,
) : LocalAuthRepository {

    private val Context.dataStore by preferencesDataStore("auth")

    override suspend fun saveUser(phone: String, token: String) {
        context.dataStore.edit {
            it[PhoneKey] = phone
            it[TokenKey] = token
        }
    }

    override suspend fun getScope(): AuthScope? {
        return context.dataStore.data.first().let {
            val phone = it[PhoneKey] ?: return@let null
            val token = it[TokenKey] ?: return@let null
            AuthScope(
                phone = phone, token = token
            )
        }
    }


    companion object {
        val PhoneKey = stringPreferencesKey("phone")
        val TokenKey = stringPreferencesKey("token")
    }

}