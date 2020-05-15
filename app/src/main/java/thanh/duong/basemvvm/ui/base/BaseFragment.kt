package thanh.duong.basemvvm.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseFragment : Fragment() {

    protected var rootView: View? = null
    fun isAlive(): Boolean = lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        rootView = inflater.inflate(getLayout(), container, false)

        if(userVisibleHint){
            // fragment is visible
            onFragmentVisible()
        }
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSyncView()
        onSyncData()
        onSyncEvent()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser && isResumed) {
            // fragment is visible and have created
            onFragmentVisible()
        }
    }

    protected abstract fun getLayout(): Int
    protected open fun onSyncView() {}
    protected open fun onSyncData() {}
    protected open fun onSyncEvent() {}
    protected open fun onFragmentVisible() {}
}