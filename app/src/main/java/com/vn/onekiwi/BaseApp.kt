package com.vn.onekiwi

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.vn.onekiwi.koin.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

/**
 * https://prinzly.medium.com/android-clean-architecture-with-kotlin-coroutines-koin-and-retrofit-6d6257ce2900
 * https://github.com/GDGVIT/handly-app
 * */
class BaseApp: Application(), KoinComponent {

    companion object {
        lateinit var getContext: Context
        //lateinit var prefs: SharedPrefs
    }
    override fun onCreate() {
        super.onCreate()
        getContext = applicationContext
        //prefs = SharedPrefs(applicationContext)

        initKoin()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(appModules)
        }
    }
}