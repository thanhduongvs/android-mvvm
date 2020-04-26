package thanh.duong.koinmvvm.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())

        onSyncViews()
        onSyncEvents()
        onSyncData()
    }

    protected abstract fun setLayout(): Int
    protected open fun onSyncViews() {
    }

    protected open fun onSyncEvents() {
    }

    protected open fun onSyncData() {
    }

    fun getContext() = this

    protected fun getRootView(): View? {
        return window.decorView.rootView
    }
}