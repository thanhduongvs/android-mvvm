package thanh.duong.basemvvm.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import thanh.duong.basemvvm.R
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity: AppCompatActivity(), CoroutineScope{

    protected lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
        setContentView(getLayout())
        pendingTransition()
        onSyncView()
        onSyncData()
        onSyncEvent()
    }

    override val coroutineContext: CoroutineContext = job + Dispatchers.Main

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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