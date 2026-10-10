package com.example.soile

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soile.ui.theme.SoileTheme
import kotlinx.coroutines.delay
import topics

const val TALK_SECONDS = 5

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SoileTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val shuffledTopics = remember {topics.shuffled()}
                    var topicNumber by remember { mutableIntStateOf(0) }
                    val topic  = shuffledTopics[topicNumber]
                    var started by remember { mutableStateOf(false) }
                    var answerTime by remember { mutableIntStateOf(TALK_SECONDS) }

                    Column(
                        modifier = Modifier.padding(innerPadding).padding(horizontal = 24.dp).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Soile",
                            fontSize = 14.sp
                        )
                        Text(
                            text = topic,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (started) {
                            Text(
                                text = "$answerTime",
                                fontSize = 72.sp,
                                textAlign = TextAlign.Center
                            )

                            LinearProgressIndicator(
                                modifier = Modifier.fillMaxWidth(),
                                progress = {answerTime.toFloat() / TALK_SECONDS.toFloat()}
                            )
                        } else if (answerTime > 0) {
                            Button(onClick = { started = true }) {
                                Text("START")
                            }
                        } else {
                            Text(
                                text="Time's up!",
                                fontSize = 28.sp,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Button(onClick = {
                                answerTime = TALK_SECONDS
                                topicNumber = (topicNumber + 1) % shuffledTopics.size
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
