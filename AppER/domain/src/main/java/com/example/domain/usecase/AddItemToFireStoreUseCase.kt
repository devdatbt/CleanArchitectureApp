package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository

class AddItemToFireStoreUseCase constructor(private val serviceRepository: AccountServiceRepository) {
    suspend fun invoke(
        timeStamp: String, hashMap: HashMap<String, String>, isSuccess: (Boolean) -> Unit
    ) = serviceRepository.addItemToFireStore(timeStamp, hashMap, isSuccess)
}