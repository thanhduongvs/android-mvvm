package thanh.duong.basemvvm

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import thanh.duong.basemvvm.injection.appModules
import thanh.duong.basemvvm.utils.SharedPrefs

class BaseApp: Application(){

    companion object {
        lateinit var getContext: Context
        lateinit var prefs: SharedPrefs
    }

    override fun onCreate() {
        super.onCreate()

        getContext = applicationContext
        prefs = SharedPrefs(applicationContext)

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

/*
https://github.com/MindorksOpenSource/Retrofit-Kotlin-Coroutines-Example
https://github.com/gildor/kotlin-coroutines-retrofit
https://github.com/eric-ampire/android-clean-architecture
https://github.com/ATechnoHazard/twist.moe/blob/master/app/src/main/java/dev/smoketrees/twist/api/BaseApiClient.kt
https://github.com/serhii-pokrovskyi/Android-modern-architecture/blob/master/Kotlin-mvvm-arch-coroutines/app/src/main/java/one/brainyapps/taskmanager/util/helper/LiveEvent.kt
https://github.com/nikb7/android-boilerplate/blob/master/app/src/main/java/com/techonlabs/androidboilerplate/ApplicationGlideModule.kt
https://github.com/ademgunay/DemoMeow/tree/master
https://github.com/ademgunay/DemoMeow

https://github.com/RajaArslanAkber/WrapContentViewPager
https://viblo.asia/p/thay-the-progressdialog-bang-progressbutton-trong-ung-dung-cua-ban-oOVlYpjvZ8W
https://github.com/razir/ProgressButton

https://github.com/meihuali/TuiTuiZhu
https://github.com/7449/XAdapter
https://github.com/TStason/SwipeRevealLayout
https://github.com/rolandwu23/room_chat_medium/tree/master
https://github.com/AgnaldoNP/PreviewImageCollection
https://android-arsenal.com/details/1/8055
 */