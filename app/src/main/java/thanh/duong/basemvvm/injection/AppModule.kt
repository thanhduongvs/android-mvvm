package thanh.duong.basemvvm.injection

import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import thanh.duong.basemvvm.network.BaseClient
import thanh.duong.basemvvm.network.BaseClient.createHttpClient
import thanh.duong.basemvvm.network.repository.UserRepo
import thanh.duong.basemvvm.network.service.UserClient
import thanh.duong.basemvvm.network.service.UserService
import thanh.duong.basemvvm.ui.main.MainViewModel


//private const val BASE_URL = "https://api.github.com/"
//var BASE_URL = "https://wallet.joco.asia:8888/"
var BASE_URL = "https://api.thecatapi.com/v1/"

val appModule = module(override = true) {
    single { androidApplication().resources }
}

val networkModule get() = module {
    single (createdAtStart = true){ createHttpClient() }
    single (createdAtStart = true){ BaseClient.createService<UserService>(get(), BASE_URL) }
    single (createdAtStart = true){ UserClient(get()) }
    single (createdAtStart = true){ UserRepo(get()) }
    //factory { UserClient(get()) }
    //factory { UserRepo(get()) }
}

val contextModule = module(override = true) {

    viewModel { MainViewModel(get()) }
}

val appModules = listOf(
    appModule, networkModule, contextModule)