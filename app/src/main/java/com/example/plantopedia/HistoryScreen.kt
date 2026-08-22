package com.example.plantopedia

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun HistoryScreen() {

    val context = LocalContext.current

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val softGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)

    var historyItems by remember {
        mutableStateOf(
            ScanHistory.getAll(context)
        )
    }

    LaunchedEffect(Unit) {
        historyItems = ScanHistory.getAll(context)
    }

    val todayItems =
        historyItems.filter {
            isToday(it.timestamp)
        }

    val earlierItems =
        historyItems.filter {
            !isToday(it.timestamp)
        }

    Scaffold(
        containerColor = backgroundColor
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),

            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                top = 24.dp,
                bottom = 24.dp
            ),

            verticalArrangement =
                Arrangement.spacedBy(12.dp)
        ) {

            item {
                Text(
                    text = stringResource(id = R.string.scan_history_title),
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = darkGreen
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = stringResource(id = R.string.scan_history_subtitle),
                    style = MaterialTheme.typography.bodyLarge,
                    color = grayText
                )

                Spacer(modifier = Modifier.height(12.dp))
            }

            if (historyItems.isEmpty()) {

                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        color = Color.White,
                        tonalElevation = 2.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(28.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(RoundedCornerShape(45.dp))
                                    .background(softGreen),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "🌿",
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }

                            Spacer(modifier = Modifier.height(18.dp))

                            Text(
                                text = stringResource(id = R.string.no_scans_yet),
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                color = darkGreen
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stringResource(id = R.string.no_scans_yet_desc),
                                style = MaterialTheme.typography.bodyLarge,
                                color = grayText
                            )
                        }
                    }
                }

            } else {

                if (todayItems.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(id = R.string.history_today),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = grayText
                        )
                    }

                    items(todayItems) { item ->
                        HistoryItem(
                            item = item,
                            darkGreen = darkGreen,
                            orange = orange
                        )
                    }
                }

                if (earlierItems.isNotEmpty()) {
                    item {
                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = stringResource(id = R.string.history_earlier),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = grayText
                        )
                    }

                    items(earlierItems) { item ->
                        HistoryItem(
                            item = item,
                            darkGreen = darkGreen,
                            orange = orange
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HistoryItem(
    item: ScanHistoryItem,
    darkGreen: Color,
    orange: Color
) {
    val context = LocalContext.current
    val emoji = getCropEmoji(item.crop)
    val diseaseInfo = DiseaseDatabase.get(item.disease, context)

    val displayCrop = diseaseInfo?.crop ?: item.crop
    val displayDisease = diseaseInfo?.disease ?: formatLabel(item.disease)

    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        color = Color.White,
        tonalElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE7F0E8)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = emoji,
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            Spacer(modifier = Modifier.size(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = displayDisease,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = darkGreen
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = stringResource(
                        id = R.string.history_confidence_format,
                        displayCrop,
                        item.confidence * 100
                    ),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = formatTime(item.timestamp),
                    style = MaterialTheme.typography.bodySmall,
                    color = orange
                )
            }
        }
    }
}

fun isToday(timestamp: Long): Boolean {
    val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
    val today = dateFormat.format(Date())
    val itemDate = dateFormat.format(Date(timestamp))
    return today == itemDate
}

fun formatTime(timestamp: Long): String {
    return SimpleDateFormat("dd MMM yyyy • hh:mm a", Locale.getDefault()).format(Date(timestamp))
}

fun getCropEmoji(crop: String): String {
    return when {
        crop.contains("Tomato", ignoreCase = true) -> "🍅"
        crop.contains("Potato", ignoreCase = true) -> "🥔"
        crop.contains("Apple", ignoreCase = true) -> "🍎"
        crop.contains("Grape", ignoreCase = true) -> "🍇"
        crop.contains("Corn", ignoreCase = true) -> "🌽"
        crop.contains("Pepper", ignoreCase = true) -> "🌶️"
        else -> "🌿"
    }
}