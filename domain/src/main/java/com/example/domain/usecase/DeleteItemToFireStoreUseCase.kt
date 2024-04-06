package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository
import javax.inject.Inject

class DeleteItemToFireStoreUseCase @Inject constructor(private val accountServiceRepository: AccountServiceRepository) {
    suspend fun invoke(
        timeStamp: String,
        isSuccess: (Boolean) -> Unit
    ) = accountServiceRepository.deleteItemToFireStore(timeStamp, isSuccess)
}