package com.vn.onekiwi.fragments.second

import android.view.View
import com.vn.onekiwi.base.BaseFragment
import com.vn.onekiwi.databinding.FragmentSecondBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class SecondFragment: BaseFragment<SecondContract.Presenter>(), SecondContract.View {
    private lateinit var binding: FragmentSecondBinding
    override val presenter: SecondContract.Presenter by inject { parametersOf(this) }

    companion object {
        fun newInstance(): SecondFragment {
            return SecondFragment()
        }
    }

    override fun onGetView(): View {
        binding = FragmentSecondBinding.inflate(layoutInflater)
        return binding.root
    }
}