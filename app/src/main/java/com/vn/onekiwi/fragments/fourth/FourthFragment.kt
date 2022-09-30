package com.vn.onekiwi.fragments.fourth

import android.view.View
import com.vn.onekiwi.base.BaseFragment
import com.vn.onekiwi.databinding.FragmentFourthBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FourthFragment: BaseFragment<FourthContract.Presenter>(), FourthContract.View {
    private lateinit var binding: FragmentFourthBinding
    override val presenter: FourthContract.Presenter by inject { parametersOf(this) }

    companion object {
        fun newInstance(): FourthFragment {
            return FourthFragment()
        }
    }

    override fun onGetView(): View {
        binding = FragmentFourthBinding.inflate(layoutInflater)
        return binding.root
    }

}