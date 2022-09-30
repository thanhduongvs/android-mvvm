package com.vn.onekiwi.fragments.third

import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class ThirdContract {
    interface View : BaseView {
    }

    abstract class Presenter(view: View?) : BasePresenter<View>(view) {

    }
}