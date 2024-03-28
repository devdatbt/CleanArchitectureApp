package com.example.apper.ui.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {
    private val TAG = BaseViewModel::class.java.simpleName

    fun launchDataLoad(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            try {
                Log.e(TAG, "launchDataLoad is run with thread: ${Thread.currentThread().name}")
                block.invoke()
            } catch (error: Throwable) {
                error.printStackTrace()
            } finally {
                Log.e(TAG, "launchDataLoad is finally with thread: ${Thread.currentThread().name}")
            }
        }
    }
}