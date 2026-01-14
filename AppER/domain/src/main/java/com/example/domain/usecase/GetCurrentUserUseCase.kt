package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository

class GetCurrentUserUseCase(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke(): String = accountServiceRepository.currentUserId
}