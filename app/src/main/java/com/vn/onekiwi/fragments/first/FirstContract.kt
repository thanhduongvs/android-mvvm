package com.vn.onekiwi.fragments.first

import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class FirstContract {
    interface View : BaseView {
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}
