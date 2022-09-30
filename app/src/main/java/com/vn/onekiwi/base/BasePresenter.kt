package com.vn.onekiwi.base

import android.os.Bundle
import java.util.concurrent.atomic.AtomicBoolean

abstract class BasePresenter<T: BaseView>(private var view: T?){

    private val isViewAlive = AtomicBoolean()

    open fun initialize(extras: Bundle?){}

    open fun start(){
        isViewAlive.set(true)
    }
    open fun finalizeView(){
        isViewAlive.set(false)
    }

    fun onDetachView() {
        view = null
    }

}