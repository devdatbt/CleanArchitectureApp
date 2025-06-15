package com.example.data.models

import com.example.domain.model.Currency
import com.squareup.moshi.Json

data class CurrencyDataDto(
    @Json(name = "privacy")
    var privacy: String? = "",

    @Json(name = "quotes")
    var quotesDto: QuotesDto,

    @Json(name = "source")
    var source: String? = "",

    @Json(name = "success")
    var success: Boolean,

    @Json(name = "terms")
    var terms: String? = "",

    @Json(name = "timestamp")
    var timestamp: Int? = null,

) {
    private fun getUsdVnd(quotesDto: QuotesDto) = quotesDto.usdVnd
    fun toCurrency() = Currency(
        privacy = privacy,
        usdVnd = getUsdVnd(quotesDto),
        source = source,
        success = success,
        terms = terms,
        timestamp = timestamp
    )
}