package com.example.plantopedia

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun AdvisorScreen() {

    val coroutineScope = rememberCoroutineScope()

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val lightGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)

    var question by remember { mutableStateOf("") }
    var advice by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    val q1 = stringResource(id = R.string.question_1)
    val q2 = stringResource(id = R.string.question_2)
    val q3 = stringResource(id = R.string.question_3)
    val q4 = stringResource(id = R.string.question_4)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 30.dp
            ),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.advisor_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = darkGreen
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(id = R.string.advisor_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = grayText
                )

                Spacer(modifier = Modifier.height(14.dp))
            }

            item {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    tonalElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(lightGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🌱",
                                    style = MaterialTheme.typography.headlineMedium
                                )
                            }

                            Spacer(modifier = Modifier.size(14.dp))

                            Column {
                                Text(
                                    text = stringResource(R.string.advisor_card_title),
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = darkGreen
                                )

                                Spacer(modifier = Modifier.height(3.dp))

                                Text(
                                    text = stringResource(R.string.advisor_card_subtitle),
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = grayText
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(18.dp))

                        Text(
                            text = stringResource(R.string.advisor_intro),
                            style = MaterialTheme.typography.bodyLarge,
                            color = darkGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
            }

            item {
                Text(
                    text = stringResource(R.string.suggested_questions),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = darkGreen
                )

                Spacer(modifier = Modifier.height(2.dp))
            }

            item {
                AdvisorQuestionCard(
                    question = q1,
                    orange = orange,
                    darkGreen = darkGreen,
                    onClick = {
                        question = q1
                        advice = null
                    }
                )
            }

            item {
                AdvisorQuestionCard(
                    question = q2,
                    orange = orange,
                    darkGreen = darkGreen,
                    onClick = {
                        question = q2
                        advice = null
                    }
                )
            }

            item {
                AdvisorQuestionCard(
                    question = q3,
                    orange = orange,
                    darkGreen = darkGreen,
                    onClick = {
                        question = q3
                        advice = null
                    }
                )
            }

            item {
                AdvisorQuestionCard(
                    question = q4,
                    orange = orange,
                    darkGreen = darkGreen,
                    onClick = {
                        question = q4
                        advice = null
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(10.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp),
                    color = Color.White,
                    tonalElevation = 2.dp
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Text(
                            text = stringResource(R.string.ask_own_question),
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = darkGreen
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = stringResource(R.string.ask_question_desc),
                            style = MaterialTheme.typography.bodyMedium,
                            color = grayText
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        OutlinedTextField(
                            value = question,
                            onValueChange = {
                                question = it
                                advice = null
                            },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 5,
                            shape = RoundedCornerShape(18.dp),
                            placeholder = {
                                Text(text = stringResource(R.string.type_message_placeholder))
                            },
                            singleLine = false,
                            enabled = !isLoading
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        Button(
                            onClick = {
                                if (question.trim().isNotEmpty() && !isLoading) {
                                    coroutineScope.launch {
                                        isLoading = true
                                        advice = null
                                        advice = GeminiService.getAdvice(question.trim())
                                        isLoading = false
                                    }
                                }
                            },
                            enabled = !isLoading && question.trim().isNotEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(58.dp),
                            shape = RoundedCornerShape(18.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = orange,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = if (isLoading) {
                                    "Getting advice..."
                                } else {
                                    stringResource(R.string.get_advice)
                                },
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            if (advice != null) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        color = Color.White,
                        tonalElevation = 2.dp
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text(
                                text = stringResource(R.string.advice_result_title),
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = darkGreen
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = advice!!,
                                style = MaterialTheme.typography.bodyLarge,
                                color = darkGreen
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(R.string.advisor_disclaimer),
                    style = MaterialTheme.typography.bodyMedium,
                    color = grayText
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun AdvisorQuestionCard(
    question: String,
    orange: Color,
    darkGreen: Color,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFF3E5DB)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✦",
                    color = orange,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Text(
                text = question,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyLarge,
                color = darkGreen
            )

            Text(
                text = "›",
                style = MaterialTheme.typography.headlineSmall,
                color = orange
            )
        }
    }
}