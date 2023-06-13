package com.example.data

import com.example.data.datasource.remote.CurrencyApiService
import com.example.data.models.CurrencyDataDto
import com.example.data.models.QuotesDto
import com.example.data.repository.CurrencyRepositoryImpl
import io.reactivex.rxjava3.core.Observable
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CurrencyRepositoryImplTest {

    private lateinit var currencyRepositoryImpl: CurrencyRepositoryImpl

    @Mock
    lateinit var apiService: CurrencyApiService

    @Before
    fun setUp() {
        currencyRepositoryImpl = CurrencyRepositoryImpl(apiService)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testGetCurrencyApiSuccess() {

        val accessKey = "843d4d34ae72b3882e3db642c51e28e6"
        val currencies = "VND"
        val source = "USD"
        val format = 1

        val currencyDataDto = getCurrencyDataDto()
        `when`(apiService.getCurrencyVND(accessKey, currencies, source, format))
            .thenReturn(Observable.just(currencyDataDto))

        val expectedCurrencyData = getCurrencyDataDto()
        currencyRepositoryImpl.getCurrencyFromServer().subscribe { actualCurrencyData ->
            assertEquals(expectedCurrencyData, actualCurrencyData)
        }
    }

    private fun getCurrencyDataDto(): CurrencyDataDto {
        return CurrencyDataDto(privacy = "privacy", quotesDto = QuotesDto(24656f),
            source = "source", success = true, timestamp = 17626262, terms = "terms")
    }
}