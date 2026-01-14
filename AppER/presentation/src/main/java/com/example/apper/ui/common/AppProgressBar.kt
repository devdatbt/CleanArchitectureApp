package com.example.apper.ui.common

import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar

class AppProgressBar(
    private val progressBar: ProgressBar,
    private val countTime: Long,
    private val timeInterVal: Long
) {

    var currentProgress = 0

    private var countDownTimer = object : CountDownTimer(countTime, timeInterVal) {
        override fun onTick(p0: Long) {
            currentProgress += if (currentProgress < 5) {
                1
            } else {
                2
            }

            progressBar.progress = currentProgress
            progressBar.max = 10
        }

        override fun onFinish() {
            endLoading()
        }
    }

    fun startLoading() {
        progressBar.visibility = View.VISIBLE
        countDownTimer.start()
    }

    fun endLoading() {
        currentProgress = 0
        progressBar.visibility = View.INVISIBLE
        countDownTimer.cancel()
    }

    companion object {
        const val DEFAULT_COUNT_TIME = 10 * 1000L // 10 seconds
        const val DEFAULT_TIME_INTERVAL = 1000L // 1 second
    }
}