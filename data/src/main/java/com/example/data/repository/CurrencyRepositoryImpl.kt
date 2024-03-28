package com.example.data.repository

import com.example.data.datasource.remote.CurrencyApiService
import com.example.data.ACCESS_KEY
import com.example.data.CURRENCIES
import com.example.data.FORMAT
import com.example.data.SOURCE
import com.example.domain.model.Currency
import com.example.domain.repository.CurrencyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(private val apiService: CurrencyApiService) :
    CurrencyRepository {
    override suspend fun getCurrencyFromServer(): Currency = withContext(Dispatchers.IO) {
        apiService.getCurrencyVND(ACCESS_KEY, CURRENCIES, SOURCE, FORMAT).toCurrency()
    }
}