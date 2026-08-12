package com.example.plantopedia

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.rememberAsyncImagePainter
import java.io.File
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll


@Composable
fun CameraScreen(
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var imageCapture by remember {
        mutableStateOf<ImageCapture?>(null)
    }

    var capturedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    var prediction by remember {
        mutableStateOf<Prediction?>(null)
    }

    var isAnalyzing by remember {
        mutableStateOf(false)
    }

    val classifier = remember {
        CropDiseaseClassifier(context)
    }

    DisposableEffect(Unit) {
        onDispose {
            classifier.close()
        }
    }

    // ---------------------------------------------------------
    // CAPTURED IMAGE SCREEN
    // ---------------------------------------------------------

    if (capturedImageUri != null) {

        CapturedImageScreen(
            imageUri = capturedImageUri!!,
            prediction = prediction,
            isAnalyzing = isAnalyzing,

            onRetake = {
                capturedImageUri = null
                prediction = null
            },

            onAnalyze = {

                if (!isAnalyzing) {

                    isAnalyzing = true

                    Thread {

                        try {

                            val inputStream =
                                context.contentResolver.openInputStream(
                                    capturedImageUri!!
                                )

                            val bitmap =
                                BitmapFactory.decodeStream(
                                    inputStream
                                )

                            inputStream?.close()

                            if (bitmap != null) {

                                val result =
                                    classifier.classify(bitmap)

                                prediction = result
                            }

                        } catch (e: Exception) {

                            e.printStackTrace()

                        } finally {

                            isAnalyzing = false
                        }

                    }.start()
                }
            }
        )

        return
    }

    // ---------------------------------------------------------
    // LIVE CAMERA
    // ---------------------------------------------------------

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        AndroidView(

            factory = { ctx: Context ->

                val previewView =
                    PreviewView(ctx)

                val cameraProviderFuture =
                    ProcessCameraProvider.getInstance(ctx)

                cameraProviderFuture.addListener({

                    val cameraProvider =
                        cameraProviderFuture.get()

                    val preview =
                        Preview.Builder()
                            .build()

                    val capture =
                        ImageCapture.Builder()
                            .build()

                    imageCapture = capture

                    preview.setSurfaceProvider(
                        previewView.surfaceProvider
                    )

                    val cameraSelector =
                        CameraSelector.DEFAULT_BACK_CAMERA

                    try {

                        cameraProvider.unbindAll()

                        cameraProvider.bindToLifecycle(
                            lifecycleOwner,
                            cameraSelector,
                            preview,
                            capture
                        )

                    } catch (e: Exception) {

                        e.printStackTrace()
                    }

                }, ContextCompat.getMainExecutor(ctx))

                previewView
            },

            modifier = Modifier.fillMaxSize()
        )

        // BACK BUTTON

        TextButton(
            onClick = onBack,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {

            Text("← Back")
        }

        // CAPTURE BUTTON

        Button(
            onClick = {

                val capture =
                    imageCapture
                        ?: return@Button

                val photoFile =
                    File(
                        context.cacheDir,
                        "crop_${System.currentTimeMillis()}.jpg"
                    )

                val outputOptions =
                    ImageCapture
                        .OutputFileOptions
                        .Builder(photoFile)
                        .build()

                capture.takePicture(
                    outputOptions,
                    ContextCompat.getMainExecutor(context),

                    object :
                        ImageCapture.OnImageSavedCallback {

                        override fun onImageSaved(
                            outputFileResults:
                            ImageCapture.OutputFileResults
                        ) {

                            capturedImageUri =
                                Uri.fromFile(photoFile)
                        }

                        override fun onError(
                            exception:
                            ImageCaptureException
                        ) {

                            exception.printStackTrace()
                        }
                    }
                )

            },

            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {

            Text("📸 Capture")
        }
    }
}


// ============================================================
// CAPTURED IMAGE + RESULT
// ============================================================
@Composable
fun CapturedImageScreen(
    imageUri: Uri,
    prediction: Prediction?,
    isAnalyzing: Boolean,
    onRetake: () -> Unit,
    onAnalyze: () -> Unit
) {

    val diseaseInfo =
        prediction?.let {
            DiseaseDatabase.get(it.label)
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    )  {

        Text(
            text = "Crop Analysis",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Image(
            painter = rememberAsyncImagePainter(imageUri),

            contentDescription = "Captured crop",

            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp),

            contentScale = ContentScale.Fit
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        if (isAnalyzing) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                CircularProgressIndicator()

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text("Analyzing crop...")
            }

        } else if (prediction != null) {

            val confidence =
                prediction.confidence * 100

            val isLowConfidence =
                confidence < 40

            if (isLowConfidence) {

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "⚠️ Low Confidence",
                            style = MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "The AI is not confident enough in this result."
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text =
                                "Confidence: ${
                                    String.format(
                                        "%.1f",
                                        confidence
                                    )
                                }%"
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "Please capture another clear image of the leaf with good lighting."
                        )
                    }
                }

            } else {

                // =============================================
                // DETECTION
                // =============================================

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {

                        Text(
                            text = "🌿 ${
                                diseaseInfo?.crop
                                    ?: formatLabel(prediction.label)
                            }",
                            style =
                                MaterialTheme.typography.headlineSmall
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text =
                                prediction.label
                            ,
                            style =
                                MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

                        Text(
                            text =
                                "Confidence: ${
                                    String.format(
                                        "%.1f",
                                        confidence
                                    )
                                }%"
                        )
                    }
                }

                // =============================================
                // DISEASE INFORMATION
                // =============================================

                if (diseaseInfo != null) {

                    DiseaseInfoCard(
                        title = "🔍 Symptoms",
                        content = diseaseInfo.symptoms
                    )

                    DiseaseInfoCard(
                        title = "💊 Treatment",
                        content = diseaseInfo.treatment
                    )

                    DiseaseInfoCard(
                        title = "🛡 Prevention",
                        content = diseaseInfo.prevention
                    )

                } else {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp)
                    ) {

                        Text(
                            text =
                                "Disease information is not available for this prediction.",

                            modifier =
                                Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        // =============================================
        // BUTTONS
        // =============================================

        Row(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement =
                Arrangement.SpaceEvenly
        ) {

            OutlinedButton(
                onClick = onRetake
            ) {

                Text("↻ Retake")
            }

            Button(
                onClick = onAnalyze,

                enabled =
                    !isAnalyzing &&
                            prediction == null
            ) {

                Text("🔬 Analyze")
            }
        }
    }
}

@Composable
fun DiseaseInfoCard(
    title: String,
    content: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = title,
                style =
                    MaterialTheme.typography.titleMedium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = content,
                style =
                    MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// ============================================================
// LABEL FORMATTER
// ============================================================

fun formatLabel(
    label: String
): String {

    return label
        .replace("___", " → ")
        .replace("_", " ")
        .replace("(", " (")
        .replace(")", ")")
        .trim()
}