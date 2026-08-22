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
import androidx.compose.runtime.Composable
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

    override fun attachBaseContext(newBase: Context) {
        val lang = UserManager.getLanguagePreference(newBase)
        val localizedContext = LocaleHelper.setLocale(newBase, lang)
        super.attachBaseContext(localizedContext)
    }

    private fun showApp(cameraPermissionGranted: Boolean) {

        setContent {

            val baseContext = LocalContext.current
            var currentLanguage by remember {
                mutableStateOf(UserManager.getLanguagePreference(baseContext))
            }

            val localizedContext = remember(currentLanguage) {
                LocaleHelper.setLocale(baseContext, currentLanguage)
            }

            CompositionLocalProvider(LocalContext provides localizedContext) {

                PlantopediaTheme {

                    var isLoggedIn by remember {
                        mutableStateOf(UserManager.isLoggedIn(baseContext))
                    }

                    var currentAuthScreen by remember {
                        mutableStateOf(if (isLoggedIn) "main" else "register")
                    }

                    var currentScreen by remember {
                        mutableStateOf("home")
                    }

                    if (!isLoggedIn) {
                        when (currentAuthScreen) {
                            "register" -> {
                                RegistrationScreen(
                                    onRegisterSuccess = { selectedLang ->
                                        currentLanguage = selectedLang
                                        isLoggedIn = true
                                        currentAuthScreen = "main"
                                        currentScreen = "home"
                                    },
                                    onNavigateToLogin = {
                                        currentAuthScreen = "login"
                                    }
                                )
                            }
                            "login" -> {
                                LoginScreen(
                                    onLoginSuccess = {
                                        currentLanguage = UserManager.getLanguagePreference(baseContext)
                                        isLoggedIn = true
                                        currentAuthScreen = "main"
                                        currentScreen = "home"
                                    },
                                    onNavigateToRegister = {
                                        currentAuthScreen = "register"
                                    }
                                )
                            }
                        }
                    } else {

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
                                                Text(stringResource(R.string.nav_home))
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
                                                Text(stringResource(R.string.nav_scan))
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
                                                Text(stringResource(R.string.nav_history))
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
                                                Text(stringResource(R.string.nav_advisor))
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

                                        onLogoutClick = {
                                            UserManager.logoutUser(baseContext)
                                            isLoggedIn = false
                                            currentAuthScreen = "login"
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
    }
}
