package com.example.data

import android.util.Log

const val DATABASE_NAME = "note-db"
const val BASE_URL_CURRENCY = "http://apilayer.net/"
const val ACCESS_KEY = "843d4d34ae72b3882e3db642c51e28e6"
const val CURRENCIES = "VND"
const val SOURCE = "USD"
const val FORMAT = 1

fun main() {
    val result = returnValue(0)
    Log.e("result", result)
    stringLengthFunc("datbt")

    highLevelFunction("dat") {

    }

    val multiple2 = multiplyBy(5)
    println("result: ${multiple2(2)}")
}

fun multiplyBy(multiplier: Int): (Int) -> Int {
    return { number -> number * multiplier }
}

fun <T> applyOperation(value: T, operation: (T) -> T): T {
    return operation(value)
}

val stringLengthFunc: (String) -> Int = { input ->
    input.length
}

fun highLevelFunction(str: String, child: (String) -> Unit) {
    child.invoke("DATBT")
}

fun returnValue(number: Int?): String {
    return if (number == 0) {
        "NULL"
    } else {
        "NOT NULL"
    }
}

fun returnValue2(number: Int?): String = if (number == 0) {
    "NULL"
} else {
    "NOT NULL"
}

