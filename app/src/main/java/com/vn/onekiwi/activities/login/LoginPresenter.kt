package com.vn.onekiwi.activities.login

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.vn.onekiwi.model.Results
import com.vn.onekiwi.network.repository.UserRepo
import org.koin.core.component.KoinComponent

class LoginPresenter(view: LoginContract.View) : LoginContract.Presenter(view), KoinComponent {
    private val repo: UserRepo
    override fun checkLoginState() {
        TODO("Not yet implemented")
    }

    override fun login(email: String, password: String, activity: AppCompatActivity) {
        TODO("Not yet implemented")
        repo.getUser().observe(this, Observer {user ->
            when(user.status){
                Results.Status.LOADING -> {
                    // TODO: Hide/show some shit
                }
                Results.Status.ERROR -> {
                    Log.d("DEBUGX", "${user.message.toString()}")
                    Toast.makeText(this, "${user.message.toString()}", Toast.LENGTH_SHORT).show()
                }

                Results.Status.SUCCESS -> {
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