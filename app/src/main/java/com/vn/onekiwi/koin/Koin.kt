package com.vn.onekiwi.koin

import com.vn.onekiwi.activities.login.LoginContract
import com.vn.onekiwi.activities.login.LoginPresenter
import com.vn.onekiwi.activities.main.MainContract
import com.vn.onekiwi.activities.main.MainPresenter
import com.vn.onekiwi.fragments.first.FirstContract
import com.vn.onekiwi.fragments.first.FirstPresenter
import com.vn.onekiwi.fragments.fourth.FourthContract
import com.vn.onekiwi.fragments.fourth.FourthPresenter
import com.vn.onekiwi.fragments.second.SecondContract
import com.vn.onekiwi.fragments.second.SecondPresenter
import com.vn.onekiwi.fragments.third.ThirdContract
import com.vn.onekiwi.fragments.third.ThirdPresenter
import com.vn.onekiwi.network.BaseClient
import com.vn.onekiwi.network.BaseClient.createHttpClient
import com.vn.onekiwi.network.repository.UserRepo
import com.vn.onekiwi.network.service.UserClient
import com.vn.onekiwi.network.service.UserService
import org.koin.dsl.module

private const val BASE_URL = "https://api.github.com/"

val uiModule = module {
    factory { (view: LoginContract.View) -> LoginPresenter(view) as LoginContract.Presenter }
    factory { (view: MainContract.View) -> MainPresenter(view) as MainContract.Presenter }
    factory { (view: FirstContract.View) -> FirstPresenter(view) as FirstContract.Presenter }
    factory { (view: SecondContract.View) -> SecondPresenter(view) as SecondContract.Presenter }
    factory { (view: ThirdContract.View) -> ThirdPresenter(view) as ThirdContract.Presenter }
    factory { (view: FourthContract.View) -> FourthPresenter(view) as FourthContract.Presenter }
}

val networkModule get() = module {
    single (createdAtStart = true){ createHttpClient() }
    single (createdAtStart = true){ BaseClient.createService<UserService>(get(), BASE_URL) }
    single (createdAtStart = true){ UserClient(get()) }
    single (createdAtStart = true){ UserRepo(get()) }
}

val appModules = listOf(
    uiModule, networkModule)
//val appModules = listOf(
    //appModule, networkModule, contextModule)