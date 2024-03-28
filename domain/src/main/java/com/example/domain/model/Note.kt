package com.example.domain.model

data class Note(
    val title: String? = "",
    val content: String? = "",
    val timestamp: Long = 0
) {
    fun getTitleContainsWord(keyWord: String): Boolean = title!!.lowercase().contains(keyWord)
}