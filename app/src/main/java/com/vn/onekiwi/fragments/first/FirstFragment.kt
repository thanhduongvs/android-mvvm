package com.vn.onekiwi.fragments.first

import android.view.View
import com.vn.onekiwi.base.BaseFragment
import com.vn.onekiwi.databinding.FragmentFirstBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FirstFragment: BaseFragment<FirstContract.Presenter>(), FirstContract.View {
    private lateinit var binding: FragmentFirstBinding
    override val presenter: FirstContract.Presenter by inject { parametersOf(this) }

    companion object {
        fun newInstance(): FirstFragment {
            return FirstFragment()
        }
    }

    override fun onGetView(): View {
        binding = FragmentFirstBinding.inflate(layoutInflater)
        return binding.root
    }

}
