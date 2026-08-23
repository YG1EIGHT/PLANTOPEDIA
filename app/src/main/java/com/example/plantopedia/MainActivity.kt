package com.example.plantopedia

import android.Manifest
import android.content.Context
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.plantopedia.ui.theme.PlantopediaTheme

class MainActivity : ComponentActivity() {

    private var currentLangCode by mutableStateOf("en")

    private val cameraPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            showApp(granted)
        }

    override fun attachBaseContext(newBase: Context) {
        val lang = UserManager.getLanguage(newBase)
        val contextWithLocale = LocaleHelper.applyLanguage(newBase, lang)
        super.attachBaseContext(contextWithLocale)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentLangCode = UserManager.getLanguage(this)

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

    private fun updateLanguage(langCode: String) {
        UserManager.setLanguage(this, langCode)
        currentLangCode = langCode
        recreate()
    }

    private fun showApp(cameraPermissionGranted: Boolean) {
        setContent {
            val localizedContext = remember(currentLangCode) {
                LocaleHelper.applyLanguage(this, currentLangCode)
            }

            CompositionLocalProvider(LocalContext provides localizedContext) {
                PlantopediaTheme {
                    var isAuthCompleted by remember {
                        mutableStateOf(UserManager.isLoggedIn(this) || UserManager.isGuest(this))
                    }

                    if (!isAuthCompleted) {
                        AuthScreen(
                            onAuthSuccess = {
                                isAuthCompleted = true
                            },
                            onLanguageChanged = { langCode ->
                                updateLanguage(langCode)
                            }
                        )
                    } else {
                        var currentScreen by remember {
                            mutableStateOf("home")
                        }

                        Scaffold(
                            bottomBar = {
                                if (currentScreen != "camera") {
                                    NavigationBar {
                                        // HOME
                                        NavigationBarItem(
                                            selected = currentScreen == "home",
                                            onClick = { currentScreen = "home" },
                                            icon = {
                                                Text(
                                                    text = "⌂",
                                                    style = MaterialTheme.typography.headlineSmall
                                                )
                                            },
                                            label = {
                                                Text(stringResource(id = R.string.nav_home))
                                            }
                                        )

                                        // SCAN
                                        NavigationBarItem(
                                            selected = currentScreen == "camera",
                                            onClick = { currentScreen = "camera" },
                                            icon = {
                                                Text(
                                                    text = "📷",
                                                    style = MaterialTheme.typography.titleLarge
                                                )
                                            },
                                            label = {
                                                Text(stringResource(id = R.string.nav_scan))
                                            }
                                        )

                                        // HISTORY
                                        NavigationBarItem(
                                            selected = currentScreen == "history",
                                            onClick = { currentScreen = "history" },
                                            icon = {
                                                Text(
                                                    text = "◷",
                                                    style = MaterialTheme.typography.headlineSmall
                                                )
                                            },
                                            label = {
                                                Text(stringResource(id = R.string.nav_history))
                                            }
                                        )

                                        // ADVISOR
                                        NavigationBarItem(
                                            selected = currentScreen == "advisor",
                                            onClick = { currentScreen = "advisor" },
                                            icon = {
                                                Text(
                                                    text = "✦",
                                                    style = MaterialTheme.typography.headlineSmall
                                                )
                                            },
                                            label = {
                                                Text(stringResource(id = R.string.nav_advisor))
                                            }
                                        )
                                    }
                                }
                            }
                        ) { innerPadding ->
                            when (currentScreen) {
                                "home" -> {
                                    HomeScreen(
                                        cameraPermissionGranted = cameraPermissionGranted,
                                        onScanClick = { currentScreen = "camera" },
                                        onHistoryClick = { currentScreen = "history" },
                                        onLanguageChanged = { langCode ->
                                            updateLanguage(langCode)
                                        },
                                        onLogout = {
                                            isAuthCompleted = false
                                        },
                                        modifier = Modifier.padding(innerPadding)
                                    )
                                }

                                "camera" -> {
                                    CameraScreen(
                                        onBack = { currentScreen = "home" }
                                    )
                                }

                                "history" -> {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(innerPadding)
                                    ) {
                                        HistoryScreen()
                                    }
                                }

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
    }
}