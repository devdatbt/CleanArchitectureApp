package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository

class AutheticateUseCase constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke(email: String, pass: String, isSignInSuccess: (Boolean) -> Unit) =
        accountServiceRepository.authenticate(email, pass, isSignInSuccess)
}