package com.example.resumogeral

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.resumogeral.service.RetrofitBuilder
import com.example.resumogeral.model.Credentials
import com.example.resumogeral.model.LoginResponse
import com.example.resumogeral.utils.snackBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), Callback<LoginResponse> {

    private lateinit var inputEmail: EditText
    private lateinit var inputSenha: EditText
    private lateinit var buttonLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadComponents()
        loadEvents()

    }

    private fun loadEvents() {
        buttonLogin.setOnClickListener {
            fazerLogin()
        }
    }

    fun fazerLogin() {
        val email = inputEmail.toString()
        val senha = inputSenha.toString()

        val credentials = Credentials(email, senha)

        if (credentials.checkUserName() && credentials.checkUserPassword()) {

            dispararRequestLogin(credentials)

        } else {
            snackBar(inputEmail, R.string.usuario_invalido)
        }
    }

    //essa funcao depois de validar os campos informados pelo usuario
    //irá disparar uma request para api
    private fun dispararRequestLogin(credentials: Credentials) {
        //chama a implementacao do Authentication
        val interfaceDeAuthImplementada = RetrofitBuilder.getAuthenticationServices()
        //da instancia do authentication chama a funcao de login passando as credentials por parametro
        val call = interfaceDeAuthImplementada.login(credentials)
        //neste momento é disparado para a api
        call.clone().enqueue(this)
    }


    fun loadComponents() {
        inputEmail = findViewById(R.id.textEmail)
        inputSenha = findViewById(R.id.textSenha)
        buttonLogin = findViewById(R.id.buttonLogin)
    }


    //retrofit chama automaticamente esta funcao quando recebe uma resposta da api
    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
        if (response.body() != null) {
            val loginResponse = response.body()!!
            if (loginResponse.isError()) {
                snackBar(inputEmail, R.string.usuario_invalido)
            } else {
                Intent(this, MainActivity::class.java).apply {
                    startActivity(this)
                }
            }
        } else {
            snackBar(inputEmail, R.string.usuario_invalido)
        }

    }

    //retrofit chama automaticamente esta funcao quando um erro de conexao ou conversao json
    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
        snackBar(inputEmail, R.string.usuario_invalido)
    }
}
