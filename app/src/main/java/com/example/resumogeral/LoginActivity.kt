package com.example.resumogeral

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import com.example.resumogeral.model.Credentials
import com.example.resumogeral.utils.snackBar

class LoginActivity : AppCompatActivity() {

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

        } else {
            snackBar(inputEmail, R.string.usuario_invalido)
        }
    }


    fun loadComponents() {
        inputEmail = findViewById(R.id.textEmail)
        inputSenha = findViewById(R.id.textSenha)
        buttonLogin = findViewById(R.id.buttonLogin)
    }

}
