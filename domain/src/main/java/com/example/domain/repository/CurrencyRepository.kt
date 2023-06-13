package com.example.domain.repository

import com.example.domain.model.Currency
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

interface CurrencyRepository {
    fun getCurrencyFromServer(): Observable<Currency>
}