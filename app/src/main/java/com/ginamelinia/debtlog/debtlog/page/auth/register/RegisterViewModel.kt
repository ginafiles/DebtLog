package com.ginamelinia.debtlog.debtlog.page.auth.register

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    lateinit var sharedPref: SharedPreferences

    fun initSharedPreferences(context: Context) {
        sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun registerUser(name: String, email: String, password: String): Boolean {
        if (isInputValid(name, email, password)) {
            val editor = sharedPref.edit()
            editor.putString("name", name)
            editor.putString("email", email)
            editor.putString("password", password)
            editor.apply()
            return true
        } else {
            return false
        }
    }

    private fun isInputValid(name: String, email: String, password: String): Boolean {
        return name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password.length >= 6
    }
}