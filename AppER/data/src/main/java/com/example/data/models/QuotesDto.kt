package com.example.data.models

import com.squareup.moshi.Json

data class QuotesDto(
    @Json(name = "USDVND")
    val usdVnd: Float? = null
)