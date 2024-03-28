package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke() = accountServiceRepository.signOut()
}