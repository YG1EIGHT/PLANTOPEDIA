package com.example.plantopedia

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.core.content.ContextCompat

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

            var showCamera by remember {
                mutableStateOf(false)
            }

            if (showCamera && cameraPermissionGranted) {

                CameraScreen(
                    onBack = {
                        showCamera = false
                    }
                )

            } else {

                HomeScreen(
                    cameraPermissionGranted = cameraPermissionGranted,
                    onScanClick = {
                        showCamera = true
                    }
                )
            }
        }
    }
}