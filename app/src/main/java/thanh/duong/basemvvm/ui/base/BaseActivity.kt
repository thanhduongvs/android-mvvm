package thanh.duong.basemvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import thanh.duong.basemvvm.R

abstract class BaseActivity: AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        pendingTransition()
        onSyncView()
        onSyncData()
        onSyncEvent()
    }

    protected abstract fun getLayout(): Int
    protected open fun pendingTransition(
        slideIn: Int = R.anim.slide_in_right, slideOut: Int = R.anim.slide_out_left
    ) {
        overridePendingTransition(slideIn, slideOut)
    }
    protected open fun onSyncView() {}
    protected open fun onSyncData() {}
    protected open fun onSyncEvent() {}
}