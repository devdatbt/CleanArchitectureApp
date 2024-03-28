package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository
import javax.inject.Inject

class AutheticateUseCase @Inject constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke(email: String, pass: String) =
        accountServiceRepository.authenticate(email, pass)
}