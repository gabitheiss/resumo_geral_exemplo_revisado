package com.example.resumogeral.model

import com.google.gson.annotations.SerializedName

data class Credentials(

    @SerializedName("username") val email: String,
    @SerializedName("password") val password: String
){
    fun checkUserName():Boolean{
        return email.isEmpty()
    }
    fun checkUserPassword():Boolean{
        return password.isEmpty()
    }
}
