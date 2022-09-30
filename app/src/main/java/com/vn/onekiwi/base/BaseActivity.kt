package com.vn.onekiwi.base

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


/**
* https://iebayirli.medium.com/base-configuration-for-android-mvp-design-pattern-13c1fad1f9ee
* https://github.com/nagycsongor98/RateMyTeacher
* https://github.com/hanilozmen/Base-MVP-Kotlin-Fragments
* https://github.com/codexSD/BankSMSParser/tree/main/app/src/main/java/com/codexSoftSD/bankCardsTemplateManager/ui/base
 * https://github.com/GDGVIT/handly-app MVP
* */
abstract class BaseActivity<T: BasePresenter<*>> : AppCompatActivity(), BaseView {

    protected abstract val presenter : T

    protected abstract fun onGetView(): View
    protected open fun onSyncView() {}
    protected open fun onSyncData() {}
    protected open fun onSyncEvent() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(onGetView())
        presenter.initialize(intent.extras)
        onSyncView()
        onSyncData()
        onSyncEvent()
    }

    override fun onStart() {
        super.onStart()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
        presenter.finalizeView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun toast(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }
}