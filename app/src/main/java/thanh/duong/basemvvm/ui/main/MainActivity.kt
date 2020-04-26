package thanh.duong.basemvvm.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.getKoin
import thanh.duong.basemvvm.R
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import thanh.duong.basemvvm.injection.BASE_URL
import thanh.duong.basemvvm.injection.appModules
import thanh.duong.basemvvm.utils.loadImageSsl
import thanh.duong.basemvvm.network.middleware.Result
import thanh.duong.koinmvvm.base.BaseActivity

class MainActivity : BaseActivity(), View.OnClickListener {

    //private val viewModel: MainViewModel by viewModel()
    private val viewModel by viewModel<MainViewModel>()

    override fun setLayout(): Int = R.layout.activity_main

    override fun onSyncViews() {
        super.onSyncViews()
        button_change.setOnClickListener(this)
        getData()
        image_view.loadImageSsl("https://wallet.joco.asia:8888/icon_chart_data/bch.png")

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            button_change.id -> {
                reloadNetwork()
                getData()
            }
        }
    }

    fun reloadNetwork(){
        BASE_URL = "https://api.github.com/"
        stopKoin()
        startKoin { modules(appModules) }
        //getKoin().createScope("myScope1",named("SCOPE_NAME"))
    }

    fun getData(){
        viewModel.getCryptoCurrency().observe(this, Observer {user ->
            when(user.status){
                Result.Status.LOADING -> {
                    // TODO: Hide/show some shit
                }
                Result.Status.ERROR -> {
                    Log.d("DEBUGX", "${user.message.toString()}")
                    Toast.makeText(this, "${user.message.toString()}", Toast.LENGTH_SHORT).show()
                }

                Result.Status.SUCCESS -> {
                    user.data?.let {
                        //Toast.makeText(this, "${user.data?.result?.get(0)?.name}", Toast.LENGTH_LONG).show()
                        for(item in user.data.result!!){
                            Log.d("DEBUGX", "${item.name}")
                        }
                    }
                }
            }

        })
    }
}
