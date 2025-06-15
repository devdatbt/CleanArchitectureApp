package com.example.domain.usecase

import com.example.domain.repository.CurrencyRepository

class GetCurrencyUseCase constructor(private val currencyRepository: CurrencyRepository) {
    suspend fun invoke() = currencyRepository.getCurrencyFromServer()
}