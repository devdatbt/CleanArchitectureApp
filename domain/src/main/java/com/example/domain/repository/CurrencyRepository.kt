package com.example.domain.repository

import com.example.domain.model.Currency

interface CurrencyRepository {
    suspend fun getCurrencyFromServer(): Currency
}