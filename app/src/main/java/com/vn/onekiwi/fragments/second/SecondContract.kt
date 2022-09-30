package com.vn.onekiwi.fragments.second

import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class SecondContract {
    interface View : BaseView {
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {
    }
}