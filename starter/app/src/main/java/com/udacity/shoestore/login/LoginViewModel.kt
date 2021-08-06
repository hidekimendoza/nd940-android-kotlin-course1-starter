package com.udacity.shoestore.login

import androidx.lifecycle.ViewModel
import timber.log.Timber

class LoginViewModel : ViewModel() {
    private val validUsers = mutableListOf<String>()
    private val validPassword: String = "password"

    init {
        Timber.i("Login View Model Created")
        validUsers.add("guess")
    }

    fun loginAuthentication(prompt_user: String, prompt_pwd: String): Boolean {
        if (prompt_user in validUsers && prompt_pwd == validPassword) {
            return true
        }
        return false
    }

}
