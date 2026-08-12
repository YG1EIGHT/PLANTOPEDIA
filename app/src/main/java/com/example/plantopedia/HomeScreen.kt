package com.example.plantopedia

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    cameraPermissionGranted: Boolean,
    onScanClick: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "🌱 Plantopedia",
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = "AI Crop Disease Detection",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onScanClick
            ) {
                Text("📷 Scan Crop")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            if (cameraPermissionGranted) {

                Text(
                    text = "Camera ready ✓"
                )

            } else {

                Text(
                    text = "Camera permission required"
                )
            }
        }
    }
}