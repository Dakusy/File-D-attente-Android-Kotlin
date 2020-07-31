package com.example.file_attente.Remote

import com.example.file_attente.Models.APIResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IMyAPI {
    @FormUrlEncoded
    @POST("http://192.168.2.142:1337/operators")

    fun loginClient(@Field( "name") name:String,@Field("password") password:String):Call<APIResponse>
}