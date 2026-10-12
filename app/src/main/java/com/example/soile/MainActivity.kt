package com.example.soile

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.stylusHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soile.ui.theme.SoileTheme
import kotlinx.coroutines.delay
import topics

const val TALK_SECONDS = 60
const val MAX_SKIPS = 3

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
                    var skipsLeft by remember { mutableIntStateOf(MAX_SKIPS) }

                    val progress by animateFloatAsState(
                        targetValue = if (started) (answerTime.toFloat() - 1) / TALK_SECONDS.toFloat() else 1f,
                        animationSpec = tween(
                            durationMillis = 1000,
                            easing = LinearEasing
                        ),
                    )

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(horizontal = 24.dp)
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Soile",
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.weight(1f))

                        Card (
                            shape = RoundedCornerShape(28.dp),
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
                        ) {
                            Column(
                                modifier = Modifier.padding(24.dp),
                                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Your topic",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = topic,
                                    style = MaterialTheme.typography.headlineMedium,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                        Spacer(modifier = Modifier.weight(1f))

                        Column(
                            modifier = Modifier.padding(bottom = 32.dp).height(200.dp),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,

                        )
                        {
                            if (started) {
                                Text(
                                    text = "Speak!",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                Text(
                                    text = "until the timer ends",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "$answerTime",
                                    style = MaterialTheme.typography.displayLarge,
                                    textAlign = TextAlign.Center
                                )

                                LinearProgressIndicator(
                                    modifier = Modifier.fillMaxWidth()
                                        .height(12.dp)
                                        .clip(RoundedCornerShape(6.dp)),
                                    progress = { progress },
                                    drawStopIndicator = {},
                                )
                            } else if (answerTime > 0) {
                                Button(
                                    modifier = Modifier.fillMaxWidth()
                                        .height(64.dp),
                                    shape = RoundedCornerShape(32.dp),
                                    onClick = { started = true }
                                ) {
                                    Text(
                                        "START",
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                                TextButton(
                                    onClick = {
                                        skipsLeft -= 1
                                        answerTime = TALK_SECONDS
                                        topicNumber = (topicNumber + 1) % shuffledTopics.size
                                    },
                                    enabled = skipsLeft > 0) {
                                        Text(
                                            "Skip ($skipsLeft)",
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                        )
                                }
                            } else {
                                Text(
                                    text="Time's up!",
                                    style = MaterialTheme.typography.titleLarge,
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    modifier = Modifier.fillMaxWidth()
                                        .height(64.dp),
                                    shape = RoundedCornerShape(32.dp),
                                    onClick = {
                                        answerTime = TALK_SECONDS
                                        topicNumber = (topicNumber + 1) % shuffledTopics.size
                                    }
                                ) {
                                    Text(
                                        "NEXT TOPIC",
                                        style = MaterialTheme.typography.titleLarge,
                                    )
                                }
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedButton(
                                    modifier = Modifier.fillMaxWidth(),
                                    onClick = {
                                        answerTime = TALK_SECONDS
                                    }
                                ) {
                                    Text(
                                        "AGAIN",
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }

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
