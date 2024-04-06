package com.example.apper.ui.viewmodel

import android.util.Log
import com.example.apper.ui.base.BaseViewModel
import com.example.apper.usecase.AppUseCase
import com.example.apper.utils.isValidEmail
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val appUseCase: AppUseCase) : BaseViewModel()  {

    private val TAG = LoginViewModel::class.java.simpleName

    fun authenticateEmail(email: String, password: String, isLoginSuccess: (Boolean) -> Unit) {
        if (email.isBlank()) {
            Log.d(TAG, "isValidEmail")
            isLoginSuccess.invoke(false)
            return
        }
        if (password.isBlank()) {
            Log.d(TAG, "isValidPassword")
            isLoginSuccess.invoke(false)
            return
        }
        launchDataLoad {
            appUseCase.autheticateUseCase.invoke(email = email, pass = password)
            isLoginSuccess.invoke(true)
        }
    }
}