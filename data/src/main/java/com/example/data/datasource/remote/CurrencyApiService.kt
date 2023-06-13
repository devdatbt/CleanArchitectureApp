package com.example.data.datasource.remote

import com.example.data.models.CurrencyDataDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
//http://apilayer.net/api/live?access_key=843d4d34ae72b3882e3db642c51e28e6&currencies=VND&source=USD&format=1
    @GET("api/live")
    fun getCurrencyVND(
        @Query("access_key") accessKey: String,
        @Query("currencies") currencies: String,
        @Query("source") source: String,
        @Query("format") format: Int,
    ): Observable<CurrencyDataDto>
}