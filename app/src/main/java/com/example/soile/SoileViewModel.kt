package com.example.soile

import android.os.SystemClock
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import topics
import kotlin.collections.get
import kotlin.rem


class SoileViewModel : ViewModel() {
    var state by mutableStateOf(SpeechState.READY)
        private set
    var answerTime by mutableIntStateOf(TALK_SECONDS)
        private set
    var skipsLeft by mutableIntStateOf(MAX_SKIPS)
        private set
    private val shuffledTopics = topics.shuffled()
    private var topicNumber by mutableIntStateOf(0)
    val topic: String get() = shuffledTopics[topicNumber]

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


    fun onAgain() {
        state = SpeechState.READY
        timerJob?.cancel()
        answerTime = TALK_SECONDS
    }

    fun onNextTopic() {
        onAgain()
        moveToNextTopic()
    }

    fun onSkipped() {
        if (skipsLeft > 0 && state == SpeechState.READY) {
            skipsLeft -= 1
            moveToNextTopic()
        }
    }

    private fun moveToNextTopic() {
        topicNumber = (topicNumber + 1) % shuffledTopics.size
    }
}

