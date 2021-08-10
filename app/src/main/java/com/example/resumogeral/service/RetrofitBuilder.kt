package com.example.resumogeral.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitFake = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //retorna a implementacao da interface
    //retrofit le a interface e devolve a instancia implementada

    fun getAuthenticationServices(): Authentication{
        return retrofitFake.create(Authentication::class.java)
    }
    fun getProductServices(): ProductService{
        return retrofitFake.create(ProductService::class.java)
    }

}