package com.example.domain.usecase

import com.example.domain.repository.AccountServiceRepository
import javax.inject.Inject

class AddItemToFireStoreUseCase @Inject constructor(private val serviceRepository: AccountServiceRepository) {
    suspend fun invoke(
        timeStamp: String, hashMap: HashMap<String, String>, isSuccess: (Boolean) -> Unit
    ) = serviceRepository.addItemToFireStore(timeStamp, hashMap, isSuccess)
}