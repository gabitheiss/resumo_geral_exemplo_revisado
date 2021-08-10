package com.example.resumogeral.service

import com.example.resumogeral.model.Products
import retrofit2.Call
import retrofit2.http.GET


interface ProductService {

    @GET("/products")
    fun getProducts(): Call<List<Products>>

}