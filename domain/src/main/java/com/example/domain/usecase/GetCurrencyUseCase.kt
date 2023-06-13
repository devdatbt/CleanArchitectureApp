package com.example.domain.usecase

import com.example.domain.repository.CurrencyRepository
import javax.inject.Inject

class GetCurrencyUseCase constructor(private val currencyRepository: CurrencyRepository) {
    fun invoke() = currencyRepository.getCurrencyFromServer()
}