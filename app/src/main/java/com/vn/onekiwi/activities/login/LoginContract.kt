package com.vn.onekiwi.activities.login

import androidx.appcompat.app.AppCompatActivity
import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class LoginContract {
    interface View : BaseView {
        fun startMainActivity()
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
        abstract fun checkLoginState()

        abstract fun login(email: String, password: String, activity: AppCompatActivity)
    }
}