package com.ginamelinia.debtlog.debtlog.page.auth.login

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    lateinit var sharedPref: SharedPreferences

    fun initSharedPreferences(context: Context) {
        sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun setLoggedIn(isLoggedIn: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean("isLoggedIn", isLoggedIn)
        editor.apply()
    }

    fun loginUser(email: String, password: String): Boolean {
        val savedEmail = sharedPref.getString("email", "")
        val savedPassword = sharedPref.getString("password", "")

        if (email == savedEmail && password == savedPassword) {
            return true
        }
        return false
    }
}
