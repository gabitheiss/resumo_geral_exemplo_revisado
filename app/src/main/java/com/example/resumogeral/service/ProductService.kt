package com.example.resumogeral.service

import com.example.resumogeral.model.Products
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ProductService {

    @GET("/products")
    fun getProducts(): Call<List<Products>>

    //tela detalhes
    @GET("/products/{id}")
    fun getProduct(@Path("id") productId: Int): Call<Products>

}