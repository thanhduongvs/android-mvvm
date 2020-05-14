package thanh.duong.basemvvm.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.GsonBuilder

class SharedPrefs (context: Context){

    companion object {
        private const val PREF_NAME = "radio_prefs"
        const val AUTH_TOKEN = "authToken"
    }

    val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    inline fun <reified T> get(key: String): T {
        return when(T::class) {
            Boolean::class -> prefs.getBoolean(key, false) as T
            Float::class -> prefs.getFloat(key, 0f) as T
            Int::class -> prefs.getInt(key, 0) as T
            Long::class -> prefs.getLong(key, 0) as T
            String::class -> prefs.getString(key, "") as T
            else -> {
                val value = prefs.getString(key, null)
                GsonBuilder().create().fromJson(value, T::class.java)
            }
        }

    }

    fun <T> put(key: String, value: T) {
        val editor = prefs.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            else -> {
                val json = GsonBuilder().create().toJson(value)
                editor.putString(key, json as String)
            }
        }
        editor.apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun removeKey(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        editor.apply()
    }

    var authToken: String
        get() = get(AUTH_TOKEN)
        set(value) = put(AUTH_TOKEN, value)
}