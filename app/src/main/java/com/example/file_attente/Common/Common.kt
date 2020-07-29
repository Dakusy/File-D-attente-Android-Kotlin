package com.example.file_attente.Common

import com.example.file_attente.Remote.IMyAPI
import com.example.file_attente.Remote.RetrofitClient

object Common {

    val BASE_URL = "http://192.168.2.142:1337/Clients"

    val api:IMyAPI
        get() = RetrofitClient.getClient(BASE_URL).create(IMyAPI::class.java)
}