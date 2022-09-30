package com.vn.onekiwi.fragments.fourth

import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class FourthContract {
    interface View: BaseView{

    }

    abstract class Presenter(view: FourthContract.View?) : BasePresenter<FourthContract.View>(view) {

    }
}