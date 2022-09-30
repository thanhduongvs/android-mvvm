package com.vn.onekiwi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T : BasePresenter<*>> : Fragment(), BaseView {

    protected abstract val presenter : T
    protected abstract fun onGetView(): View
    protected open fun onSyncView() {}
    protected open fun onSyncData() {}
    protected open fun onSyncEvent() {}

    private var baseActivity: BaseActivity<*>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.initialize(arguments)
        return onGetView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSyncView()
        onSyncData()
        onSyncEvent()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity<*>){
            baseActivity = context
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

    override fun onStart() {
        presenter.start()
        super.onStart()
    }
    override fun onDestroyView() {
        presenter.finalizeView()
        super.onDestroyView()
    }

    override fun toast(message: String) {
        baseActivity?.toast(message)
    }

}