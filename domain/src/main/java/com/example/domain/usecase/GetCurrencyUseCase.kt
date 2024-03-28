package com.example.domain.usecase

import com.example.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseCase @Inject constructor(private val currencyRepository: CurrencyRepository) {
    suspend fun invoke() = currencyRepository.getCurrencyFromServer()
}