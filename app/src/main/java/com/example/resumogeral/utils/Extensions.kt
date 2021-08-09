package com.example.resumogeral.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.example.resumogeral.R
import com.google.android.material.snackbar.Snackbar

fun AppCompatActivity.snackBar(view: View, @StringRes resId: Int) {
    hideKeyBoard()

    setupSnackBar(view, resId, R.color.red).apply {
        this.show()
    }
}

//funcao para esconder o teclado, sempre vai ser padrao do android
fun AppCompatActivity.hideKeyBoard() {
    val imm =
        window.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

//funcao para personalizar o snackBar
private fun AppCompatActivity.setupSnackBar(
    v: View,
    @StringRes resId: Int,
    @ColorRes color: Int
): Snackbar {
    return Snackbar.make(v, resId, Snackbar.LENGTH_LONG).apply {
        view.setBackgroundColor(getColor(color))
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            gravity = Gravity.CENTER
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
}