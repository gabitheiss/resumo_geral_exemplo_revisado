package com.example.resumogeral.service

import com.example.resumogeral.model.Credentials
import com.example.resumogeral.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Authentication {


    //retrofit vai ler essa classe para montar , se baseira no @POST @GET @DELETE @PUT etc...
    @POST("/auth/login")
    fun login(@Body credentials: Credentials): Call<LoginResponse>
}
