package thanh.duong.basemvvm.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefs (context: Context){

    companion object {
        private const val PREF_NAME = "radio_prefs"

        private const val AUTH_TOKEN = "authToken"
        private const val RUN_STATUS = "run_status"
        private const val RUN_ACTION = "run_action"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    fun <T> put(key: String, value: T) {
        val editor = prefs.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            //else -> editor.putString(key, BaseApp.self()?.gSon?.toJson(data))
        }
        editor.apply()
    }

    fun <T> get(key: String, value: T) {
        val editor = prefs.edit()
        when (value) {
            is String -> editor.putString(key, value as String)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            is Float -> editor.putFloat(key, value as Float)
            is Int -> editor.putInt(key, value as Int)
            is Long -> editor.putLong(key, value as Long)
            //else -> editor.putString(key, BaseApp.self()?.gSon?.toJson(data))
        }
        editor.apply()
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun remove(key: String) {
        val editor = prefs.edit()
        editor.remove(key)
        editor.commit()
    }

    var authToken: String
        get() = prefs.getString(AUTH_TOKEN, "")!!
        set(value) = prefs.edit().putString(AUTH_TOKEN, value).apply()

    var status: Int
        get() = prefs.getInt(RUN_STATUS, 0)!!
        set(value) = prefs.edit().putInt(RUN_STATUS, value).apply()

    var action: Boolean
        get() = prefs.getBoolean(RUN_ACTION, false)!!
        set(value) = prefs.edit().putBoolean(RUN_ACTION, value).apply()

}