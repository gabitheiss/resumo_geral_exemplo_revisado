package com.example.resumogeral.model

import com.google.gson.annotations.SerializedName

// classe criada para receber a resposta do servidor ao chemar o endpoint de /login
data class LoginResponse(
    @SerializedName("token") val token: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("msg") val msg: String?
){

    // verificando se o token esta retornando, se for nullo cai aqui
    fun isError() : Boolean{
        return token == null || token.isEmpty()
    }

}


