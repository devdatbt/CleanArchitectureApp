package com.example.apper.utils

import android.annotation.SuppressLint
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Float.convertCurrency(): String {
    val formatter = DecimalFormat("#,###,###")
    return formatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Long.convertSimpleDateFormat(): String {
    val formatter = SimpleDateFormat("E, dd MMM yyyy")
    return formatter.format(Date(this))
}

fun String.convertStringToUpperCase(): String {
    return this.substring(0, 1).uppercase() + this.substring(1)
}
