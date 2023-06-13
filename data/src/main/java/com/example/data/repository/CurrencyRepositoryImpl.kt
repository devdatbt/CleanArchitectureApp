package com.example.data.repository

import com.example.data.datasource.remote.CurrencyApiService
import com.example.domain.model.Currency
import com.example.domain.repository.CurrencyRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import com.example.data.utils.ACCESS_KEY
import com.example.data.utils.CURRENCIES
import com.example.data.utils.FORMAT
import com.example.data.utils.SOURCE
import io.reactivex.rxjava3.core.Observable

class CurrencyRepositoryImpl constructor(private val apiService: CurrencyApiService) :
    CurrencyRepository {
    override fun getCurrencyFromServer(): Observable<Currency> {
        return apiService.getCurrencyVND(ACCESS_KEY, CURRENCIES, SOURCE, FORMAT)
            .subscribeOn(Schedulers.io()).map {
                it.toCurrency()
            }
    }
}