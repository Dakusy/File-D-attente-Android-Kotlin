package com.example.file_attente

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.file_attente.Common.Common
import com.example.file_attente.Models.APIResponse
import com.example.file_attente.Remote.IMyAPI
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    internal lateinit var mService:IMyAPI

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //Init mService
        mService = Common.api

        //Event
        btn_login.setOnClickListener { authentificateUser(username.text.toString(),password.text.toString()) }
    }

    private fun authentificateUser(username: String, password: String){
        mService.loginClient(username,password)
            .enqueue(object: Callback<APIResponse>{
                override fun onFailure(call: Call<APIResponse>, t: Throwable) {
                    Toast.makeText(this@Login,t!!.message,Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<APIResponse>, response: Response<APIResponse>) {
                    if(response!!.body()!!.isError){
                        Toast.makeText(this@Login, response!!.body()!!.error_msg,Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Login,"Login Success",Toast.LENGTH_SHORT).show()
                    }
                }
            })

    }
}