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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun AdvisorScreen() {

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

            text.contains("prevent") &&
                    text.contains("disease") -> {

                "To prevent plant diseases, keep good spacing between plants, " +
                        "avoid watering the leaves unnecessarily, remove infected " +
                        "plant material, keep the growing area clean, and provide " +
                        "adequate sunlight and air circulation."
            }

            text.contains("yellow") &&
                    text.contains("leaf") -> {

                "Yellow leaves can have several causes, including overwatering, " +
                        "underwatering, poor drainage, nutrient deficiency, or " +
                        "natural aging. Check the soil moisture first and make " +
                        "sure the plant has good drainage."
            }

            text.contains("water") -> {

                "Watering frequency depends on the crop, soil, temperature, " +
                        "and weather. Check the soil before watering. Water when " +
                        "the upper layer of soil begins to dry, and avoid keeping " +
                        "the roots constantly waterlogged."
            }

            text.contains("growth") ||
                    text.contains("grow") -> {

                "To improve plant growth, provide sufficient sunlight, suitable " +
                        "soil, balanced nutrients, adequate water, and good air " +
                        "circulation. Remove damaged leaves and monitor the plant " +
                        "regularly for pests or disease."
            }

            text.contains("disease") -> {

                "Inspect the leaves, stems, and fruit for unusual spots, " +
                        "discoloration, wilting, or fungal growth. Remove badly " +
                        "affected plant material and improve air circulation. " +
                        "A clear photo can also help identify the problem."
            }

            text.contains("pest") ||
                    text.contains("insect") -> {

                "Inspect the underside of leaves and young shoots for insects " +
                        "or damage. Remove visible pests when possible and keep " +
                        "the plant healthy. Monitor the plant regularly so an " +
                        "infestation can be detected early."
            }

            text.isEmpty() -> {

                "Please enter a question about your plant or crop first."
            }

            else -> {

                "For this question, start by checking the plant's leaves, " +
                        "soil moisture, sunlight, and overall growing conditions. " +
                        "If you are dealing with a specific disease or pest, " +
                        "take a clear photo of the affected part of the plant " +
                        "for further diagnosis."
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
                    text = "AI Plant Advisor",

                    style =
                        MaterialTheme.typography.headlineMedium,

                    fontWeight = FontWeight.Bold,

                    color = darkGreen
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text =
                        "Ask questions about your crops and plants.",

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
                                    text =
                                        "Plantopedia Advisor",

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
                                    text =
                                        "Your farming assistant",

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
                            text =
                                "I can help you understand plant diseases, " +
                                        "symptoms, treatments, and general crop care.",

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
                    text = "Suggested Questions",

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

                AdvisorQuestionCard(
                    question =
                        "How can I prevent plant diseases?",

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question =
                            "How can I prevent plant diseases?"

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 2
            // =================================================

            item {

                AdvisorQuestionCard(
                    question =
                        "What should I do if my leaves turn yellow?",

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question =
                            "What should I do if my leaves turn yellow?"

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 3
            // =================================================

            item {

                AdvisorQuestionCard(
                    question =
                        "How often should I water my crop?",

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question =
                            "How often should I water my crop?"

                        advice = null
                    }
                )
            }


            // =================================================
            // QUESTION 4
            // =================================================

            item {

                AdvisorQuestionCard(
                    question =
                        "How do I improve plant growth?",

                    orange = orange,

                    darkGreen = darkGreen,

                    onClick = {

                        question =
                            "How do I improve plant growth?"

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
                            text =
                                "Ask your own question",

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
                            text =
                                "Type a question about your plant or crop to get advice.",

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
                                    text =
                                        "Type a message..."
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
                                text =
                                    "🌱  Get Advice",

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
                                text =
                                    "🌿 Advice",

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
                    text =
                        "💡 Advice is for general plant-care guidance. " +
                                "For serious crop problems, consult a local agricultural expert.",

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