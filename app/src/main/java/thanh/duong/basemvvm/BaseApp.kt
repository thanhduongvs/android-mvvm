package thanh.duong.basemvvm

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import thanh.duong.basemvvm.injection.appModules

class BaseApp: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BaseApp)
            androidLogger()
            androidFileProperties()
            modules(appModules)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }
}