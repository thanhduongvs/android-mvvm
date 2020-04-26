package thanh.duong.basemvvm.ui.main

import thanh.duong.basemvvm.network.repository.UserRepo
import thanh.duong.basemvvm.ui.base.BaseViewModel

class MainViewModel(private val repo: UserRepo) : BaseViewModel() {

    fun getUser() = repo.getUser()

    fun getCryptoCurrency() = repo.getCryptoCurrency()
}