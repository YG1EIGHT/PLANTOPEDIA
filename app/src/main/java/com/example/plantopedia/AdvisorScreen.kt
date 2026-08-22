package com.example.plantopedia

import android.content.Context
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AdvisorScreen() {
    val context = LocalContext.current

    // =========================================================
    // COLORS
    // =========================================================

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val lightGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)


    // =========================================================
    // STATE
    // =========================================================

    var question by remember {
        mutableStateOf("")
    }

    var advice by remember {
        mutableStateOf<String?>(null)
    }


    // =========================================================
    // GET ADVICE FUNCTION
    // =========================================================

    fun getAdvice(userQuestion: String): String {

        val text = userQuestion.lowercase().trim()

        return when {

            text.contains("prevent") || text.contains("रोग") || text.contains("प्रतिबंध") || text.contains("रोकथाम") || text.contains("बीमारी") -> {
                context.getString(R.string.advice_prevent_disease)
            }

            text.contains("yellow") || text.contains("पीली") || text.contains("पिवळी") || text.contains("पत्ते") || text.contains("पाने") -> {
                context.getString(R.string.advice_yellow_leaves)
            }

            text.contains("water") || text.contains("पानी") || text.contains("पाणी") -> {
                context.getString(R.string.advice_watering)
            }

            text.contains("growth") || text.contains("grow") || text.contains("वृद्धि") || text.contains("वाढ") -> {
                context.getString(R.string.advice_growth)
            }

            text.contains("disease") -> {
                context.getString(R.string.advice_general_disease)
            }

            text.contains("pest") || text.contains("insect") || text.contains("कीट") || text.contains("कीटक") -> {
                context.getString(R.string.advice_pests)
            }

            text.isEmpty() -> {
                context.getString(R.string.advice_empty_question)
            }

            else -> {
                context.getString(R.string.advice_default)
            }
        }
    }


    // =========================================================
    // SCREEN
    // =========================================================

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),

            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 20.dp,
                bottom = 30.dp
            ),

            verticalArrangement =
                Arrangement.spacedBy(10.dp)
        ) {

            // =================================================
            // HEADER
            // =================================================

            item {

                Text(
                    text = stringResource(R.string.ai_plant_advisor),

                    style =
                        MaterialTheme.typography.headlineMedium,

                    fontWeight = FontWeight.Bold,

                    color = darkGreen
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = stringResource(R.string.advisor_subtitle),

                    style =
                        MaterialTheme.typography.bodyLarge,

                    color = grayText
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }


            // =================================================
            // INTRO CARD
            // =================================================

            item {

                Surface(
                    modifier = Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(24.dp),

                    color = Color.White,

                    tonalElevation = 2.dp
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp)
                    ) {

                        Row(
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(
                                        RoundedCornerShape(20.dp)
                                    )
                                    .background(lightGreen),

                                contentAlignment =
                                    Alignment.Center
                            ) {

                                Text(
                                    text = "🌱",

                                    style =
                                        MaterialTheme.typography.headlineMedium
                                )
                            }

                            Spacer(
                                modifier = Modifier.size(14.dp)
                            )

                            Column {

                                Text(
                                    text = stringResource(R.string.advisor_assistant_name),

                                    style =
                                        MaterialTheme.typography.titleLarge,

                                    fontWeight =
                                        FontWeight.Bold,

                                    color = darkGreen
                                )

                                Spacer(
                                    modifier =
                                        Modifier.height(3.dp)
                                )

                                Text(
                                    text = stringResource(R.string.advisor_assistant_role),

                                    style =
                                        MaterialTheme.typography.bodyMedium,

                                    color = grayText
                                )
                            }
                        }

                        Spacer(
                            modifier =
                                Modifier.height(18.dp)
                        )

                        Text(
                            text = stringResource(R.string.advisor_intro_desc),

                            style =
                                MaterialTheme.typography.bodyLarge,

                            color = darkGreen
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(14.dp)
                )
            }


            // =================================================
            // SUGGESTED QUESTIONS TITLE
            // =================================================

            item {

                Text(
                    text = stringResource(R.string.suggested_questions),

                    style =
                        MaterialTheme.typography.headlineSmall,

                    fontWeight =
                        FontWeight.Bold,

                    color = darkGreen
                )

                Spacer(
                    modifier =
                        Modifier.height(2.dp)
                )
            }


            // =================================================
            // QUESTION 1
            // =================================================

            item {

                val qText = stringResource(R.string.q_prevent_diseases)
                AdvisorQuestionCard(
                    question = qText,

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question = qText

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 2
            // =================================================

            item {

                val qText = stringResource(R.string.q_yellow_leaves)
                AdvisorQuestionCard(
                    question = qText,

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question = qText

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 3
            // =================================================

            item {

                val qText = stringResource(R.string.q_water_crop)
                AdvisorQuestionCard(
                    question = qText,

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question = qText

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 4
            // =================================================

            item {

                val qText = stringResource(R.string.q_improve_growth)
                AdvisorQuestionCard(
                    question = qText,

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question = qText

                        advice = null
                    }
                )
            }


            // =================================================
            // ASK YOUR OWN QUESTION
            // =================================================

            item {

                Spacer(
                    modifier =
                        Modifier.height(10.dp)
                )

                Surface(
                    modifier =
                        Modifier.fillMaxWidth(),

                    shape =
                        RoundedCornerShape(24.dp),

                    color =
                        Color.White,

                    tonalElevation =
                        2.dp
                ) {

                    Column(
                        modifier =
                            Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = stringResource(R.string.ask_own_question),

                            style =
                                MaterialTheme.typography.titleLarge,

                            fontWeight =
                                FontWeight.Bold,

                            color =
                                darkGreen
                        )

                        Spacer(
                            modifier =
                                Modifier.height(6.dp)
                        )

                        Text(
                            text = stringResource(R.string.ask_own_question_desc),

                            style =
                                MaterialTheme.typography.bodyMedium,

                            color =
                                grayText
                        )

                        Spacer(
                            modifier =
                                Modifier.height(14.dp)
                        )


                        // -------------------------------------------------
                        // TEXT BOX
                        // -------------------------------------------------

                        OutlinedTextField(

                            value =
                                question,

                            onValueChange = {

                                question = it

                                // Clear old answer when user edits question
                                advice = null
                            },

                            modifier =
                                Modifier.fillMaxWidth(),

                            minLines = 3,

                            maxLines = 5,

                            shape =
                                RoundedCornerShape(18.dp),

                            placeholder = {

                                Text(
                                    text = stringResource(R.string.type_message_placeholder)
                                )
                            },

                            singleLine = false
                        )


                        Spacer(
                            modifier =
                                Modifier.height(14.dp)
                        )


                        // -------------------------------------------------
                        // GET ADVICE BUTTON
                        // -------------------------------------------------

                        Button(

                            onClick = {

                                advice =
                                    getAdvice(question)
                            },

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .height(58.dp),

                            shape =
                                RoundedCornerShape(18.dp),

                            colors =
                                ButtonDefaults.buttonColors(
                                    containerColor =
                                        orange,

                                    contentColor =
                                        Color.White
                                )
                        ) {

                            Text(
                                text = stringResource(R.string.get_advice_button),

                                style =
                                    MaterialTheme.typography.titleMedium,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }
                }
            }


            // =================================================
            // ADVICE RESULT
            // =================================================

            if (advice != null) {

                item {

                    Surface(
                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(22.dp),

                        color =
                            Color.White,

                        tonalElevation =
                            2.dp
                    ) {

                        Column(
                            modifier =
                                Modifier.padding(20.dp)
                        ) {

                            Text(
                                text = stringResource(R.string.advice_title),

                                style =
                                    MaterialTheme.typography.titleLarge,

                                fontWeight =
                                    FontWeight.Bold,

                                color =
                                    darkGreen
                            )

                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )

                            Text(
                                text =
                                    advice!!,

                                style =
                                    MaterialTheme.typography.bodyLarge,

                                color =
                                    darkGreen
                            )
                        }
                    }
                }
            }


            // =================================================
            // DISCLAIMER
            // =================================================

            item {

                Spacer(
                    modifier =
                        Modifier.height(6.dp)
                )

                Text(
                    text = stringResource(R.string.advisor_disclaimer),

                    style =
                        MaterialTheme.typography.bodyMedium,

                    color =
                        grayText
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )
            }
        }
    }
}


// ============================================================
// SUGGESTED QUESTION CARD
// ============================================================

@Composable
fun AdvisorQuestionCard(
    question: String,
    orange: Color,
    darkGreen: Color,
    onClick: () -> Unit
) {

    Surface(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable {
                    onClick()
                },

        shape =
            RoundedCornerShape(20.dp),

        color =
            Color.White,

        tonalElevation =
            1.dp
    ) {

        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            Box(
                modifier =
                    Modifier
                        .size(52.dp)
                        .clip(
                            RoundedCornerShape(16.dp)
                        )
                        .background(
                            Color(0xFFF3E5DB)
                        ),

                contentAlignment =
                    Alignment.Center
            ) {

                Text(
                    text =
                        "✦",

                    color =
                        orange,

                    fontWeight =
                        FontWeight.Bold,

                    style =
                        MaterialTheme.typography.titleLarge
                )
            }

            Spacer(
                modifier =
                    Modifier.size(14.dp)
            )

            Text(
                text =
                    question,

                modifier =
                    Modifier.weight(1f),

                style =
                    MaterialTheme.typography.bodyLarge,

                color =
                    darkGreen
            )

            Text(
                text =
                    "›",

                style =
                    MaterialTheme.typography.headlineSmall,

                color =
                    orange
            )
        }
    }
}
