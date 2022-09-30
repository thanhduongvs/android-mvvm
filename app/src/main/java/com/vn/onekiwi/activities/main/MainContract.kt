package com.vn.onekiwi.activities.main

import com.vn.onekiwi.base.BasePresenter
import com.vn.onekiwi.base.BaseView

class MainContract {
    interface View: BaseView{

    }

    abstract class Presenter(view: View): BasePresenter<View>(view){

    }
}