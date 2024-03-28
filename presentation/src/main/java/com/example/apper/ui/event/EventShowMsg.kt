package com.example.apper.ui.event

class EventShowMsg<out T>(private val contentMsg: T) {
    var hasBeenHandled = false
        private set

    fun getContent(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            contentMsg
        }
    }

    fun peekContent(): T = contentMsg
}