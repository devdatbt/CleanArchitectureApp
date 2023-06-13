package com.example.domain.model

data class Currency(
    var privacy: String? = "",
    val usdVnd: Float? = null,
    var source: String? = "",
    var success: Boolean,
    var terms: String? = "",
    var timestamp: Int? = null
)