package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository

class SignOutUseCase constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke() = accountServiceRepository.signOut()
}