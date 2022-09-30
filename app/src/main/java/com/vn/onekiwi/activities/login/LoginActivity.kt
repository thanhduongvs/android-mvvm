package com.vn.onekiwi.activities.login

import android.util.Log
import android.view.View
import com.vn.onekiwi.activities.main.MainActivity
import com.vn.onekiwi.base.BaseActivity
import com.vn.onekiwi.databinding.ActivityLoginBinding
import com.vn.onekiwi.utils.startActivity
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LoginActivity : BaseActivity<LoginContract.Presenter>(), LoginContract.View, View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding
    override val presenter: LoginContract.Presenter by inject { parametersOf(this) }

    override fun onGetView(): View {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onSyncEvent() {
        super.onSyncEvent()
        binding.buttonLogin.setOnClickListener(this)
        binding.buttonLogin.id
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            binding.buttonLogin.id -> {
                Log.d("DEBUGX", "login")
                startActivity<MainActivity>()
            }
        }
    }

    override fun startMainActivity() {
        TODO("Not yet implemented")
    }


}