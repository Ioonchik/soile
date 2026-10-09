package com.example.soile

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.soile.ui.theme.SoileTheme
import kotlinx.coroutines.delay
import topics


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var topic by remember { mutableStateOf(topics.random()) }
                    var started by remember { mutableStateOf(false) }
                    var answerTime by remember { mutableIntStateOf(5) }

                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        Text(
                            text = topic
                        )
                        if (started) {
                            Text(
                                text = "Time left: $answerTime"
                            )
                        } else if (answerTime > 0) {
                            Button(onClick = { started = true }) {
                                Text("START")
                            }
                        } else {
                            Text(text="Time's up!")
                            Button(onClick = {
                                answerTime = 5
                                val previousTopic = topic
                                while (topic == previousTopic) {
                                    topic = topics.random()
                                }
                            }) {
                                Text("NEXT TOPIC")
                            }
                            Button(onClick = {
                                answerTime = 5
                            }) {
                                Text("AGAIN")
                            }

                        }
                    }

                    LaunchedEffect(started) {
                        if (started) {
                            while (answerTime > 0) {
                                delay(1000)
                                answerTime -= 1
                            }
                            started = false
                        }
                    }
                }
            }
        }
    }
}
