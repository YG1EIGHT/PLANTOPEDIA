package com.example.plantopedia

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.plantopedia.ui.theme.PlantopediaTheme

class MainActivity : ComponentActivity() {

    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            showApp(granted)
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionGranted =
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED

        if (permissionGranted) {

            showApp(true)

        } else {

            cameraPermissionLauncher.launch(
                Manifest.permission.CAMERA
            )
        }
    }


    private fun showApp(cameraPermissionGranted: Boolean) {

        setContent {

            PlantopediaTheme {

                var currentScreen by remember {
                    mutableStateOf("home")
                }


                Scaffold(

                    bottomBar = {

                        // Hide navigation when camera is open
                        if (currentScreen != "camera") {

                            NavigationBar {

                                // =====================================
                                // HOME
                                // =====================================

                                NavigationBarItem(

                                    selected =
                                        currentScreen == "home",

                                    onClick = {
                                        currentScreen = "home"
                                    },

                                    icon = {

                                        Text(
                                            text = "⌂",
                                            style =
                                                MaterialTheme
                                                    .typography
                                                    .headlineSmall
                                        )
                                    },

                                    label = {
                                        Text("Home")
                                    }
                                )


                                // =====================================
                                // SCAN
                                // =====================================

                                NavigationBarItem(

                                    selected =
                                        currentScreen == "camera",

                                    onClick = {
                                        currentScreen = "camera"
                                    },

                                    icon = {

                                        Text(
                                            text = "📷",
                                            style =
                                                MaterialTheme
                                                    .typography
                                                    .titleLarge
                                        )
                                    },

                                    label = {
                                        Text("Scan")
                                    }
                                )


                                // =====================================
                                // HISTORY
                                // =====================================

                                NavigationBarItem(

                                    selected =
                                        currentScreen == "history",

                                    onClick = {
                                        currentScreen = "history"
                                    },

                                    icon = {

                                        Text(
                                            text = "◷",
                                            style =
                                                MaterialTheme
                                                    .typography
                                                    .headlineSmall
                                        )
                                    },

                                    label = {
                                        Text("History")
                                    }
                                )


                                // =====================================
                                // ADVISOR
                                // =====================================

                                NavigationBarItem(

                                    selected =
                                        currentScreen == "advisor",

                                    onClick = {
                                        currentScreen = "advisor"
                                    },

                                    icon = {

                                        Text(
                                            text = "✦",
                                            style =
                                                MaterialTheme
                                                    .typography
                                                    .headlineSmall
                                        )
                                    },

                                    label = {
                                        Text("Advisor")
                                    }
                                )
                            }
                        }
                    }

                ) { innerPadding ->


                    // =============================================
                    // SCREEN CONTENT
                    // =============================================

                    when (currentScreen) {


                        // -----------------------------------------
                        // HOME
                        // -----------------------------------------

                        "home" -> {

                            HomeScreen(

                                cameraPermissionGranted =
                                    cameraPermissionGranted,

                                onScanClick = {
                                    currentScreen = "camera"
                                },

                                onHistoryClick = {
                                    currentScreen = "history"
                                },

                                modifier =
                                    Modifier.padding(innerPadding)
                            )
                        }


                        // -----------------------------------------
                        // CAMERA
                        // -----------------------------------------

                        "camera" -> {

                            CameraScreen(

                                onBack = {
                                    currentScreen = "home"
                                }
                            )
                        }


                        // -----------------------------------------
                        // HISTORY
                        // -----------------------------------------

                        "history" -> {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {

                                HistoryScreen()
                            }
                        }


                        // -----------------------------------------
                        // ADVISOR
                        // -----------------------------------------

                        "advisor" -> {

                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {

                                AdvisorScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}