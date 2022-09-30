package com.vn.onekiwi.fragments.third

import android.view.View
import com.vn.onekiwi.base.BaseFragment
import com.vn.onekiwi.databinding.FragmentThirdBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class ThirdFragment: BaseFragment<ThirdContract.Presenter>(), ThirdContract.View {
    private lateinit var binding: FragmentThirdBinding
    override val presenter: ThirdContract.Presenter by inject { parametersOf(this) }

    companion object {
        fun newInstance(): ThirdFragment {
            return ThirdFragment()
        }
    }

    override fun onGetView(): View {
        binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

}