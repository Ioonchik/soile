package com.example.soile

import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SoileViewModel : ViewModel() {
    var state by mutableStateOf(SpeechState.READY)
        private set
    var answerTime by mutableStateOf(TALK_SECONDS)
        private set

    private var timerJob: Job? = null

    fun onStartClicked() {
        timerJob?.cancel()
        state = SpeechState.SPEAKING
        timerJob = viewModelScope.launch {
            val startTime = SystemClock.elapsedRealtime()
            val totalMs = TALK_SECONDS * 1000

            while (true) {
                val elapsedTime = SystemClock.elapsedRealtime() - startTime
                answerTime = ((totalMs - elapsedTime).toInt() + 999)/ 1000
                if (answerTime <= 0) break
                delay(150)
            }

            state = SpeechState.FINISHED
        }
    }

    fun onReady() {
        state = SpeechState.READY
    }

    fun onFinished() {
        state = SpeechState.FINISHED
    }


}

