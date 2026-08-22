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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    cameraPermissionGranted: Boolean,
    onScanClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userProfile = UserManager.getUserProfile(context)
    val userName = userProfile?.name ?: "Farmer"

    // ---------------------------------------------------------
    // COLORS
    // ---------------------------------------------------------

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val lightCard = Color(0xFFEDE5D6)
    val softGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)


    // ---------------------------------------------------------
    // HOME CONTENT
    // Navigation bar is now handled by MainActivity
    // ---------------------------------------------------------

    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 18.dp,
                    bottom = 24.dp
                )
        ) {


            // =================================================
            // HEADER & USER PROFILE
            // =================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.greeting_farmer, userName),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = darkGreen
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = stringResource(R.string.home_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = grayText
                    )
                }

                OutlinedButton(
                    onClick = onLogoutClick,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = stringResource(R.string.logout),
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkGreen
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(28.dp)
            )


            // =================================================
            // CHECK MY PLANT CARD
            // =================================================

            Box(

                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(28.dp)
                    )
                    .background(lightCard)
                    .padding(
                        horizontal = 24.dp,
                        vertical = 28.dp
                    ),

                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    // -----------------------------------------
                    // CAMERA CIRCLE
                    // -----------------------------------------

                    Box(

                        modifier = Modifier
                            .size(110.dp)
                            .clip(
                                RoundedCornerShape(55.dp)
                            )
                            .background(darkGreen),

                        contentAlignment = Alignment.Center
                    ) {

                        Text(
                            text = "📷",

                            style =
                                MaterialTheme.typography.displaySmall
                        )
                    }


                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )


                    // -----------------------------------------
                    // TITLE
                    // -----------------------------------------

                    Text(
                        text = stringResource(R.string.check_my_plant),

                        style =
                            MaterialTheme.typography.headlineSmall,

                        fontWeight = FontWeight.Bold,

                        color = darkGreen
                    )


                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )


                    // -----------------------------------------
                    // DESCRIPTION
                    // -----------------------------------------

                    Text(
                        text = stringResource(R.string.check_my_plant_desc),

                        style =
                            MaterialTheme.typography.bodyLarge,

                        color = grayText
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(24.dp)
            )


            // =================================================
            // SCAN CROP BUTTON
            // =================================================

            Button(

                onClick = onScanClick,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(64.dp),

                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.buttonColors(

                    containerColor = orange,

                    contentColor = Color.White
                )
            ) {

                Text(
                    text = stringResource(R.string.scan_crop_button),

                    style =
                        MaterialTheme.typography.titleLarge,

                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // =================================================
            // CAMERA STATUS
            // =================================================

            Text(

                text =
                    if (cameraPermissionGranted) {
                        stringResource(R.string.camera_ready)
                    } else {
                        stringResource(R.string.camera_permission_required)
                    },

                style =
                    MaterialTheme.typography.bodyLarge,

                color =
                    if (cameraPermissionGranted) {
                        darkGreen
                    } else {
                        Color.Red
                    }
            )


            Spacer(
                modifier = Modifier.height(32.dp)
            )


            // =================================================
            // RECENT SCANS HEADER
            // =================================================

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(
                    text = stringResource(R.string.recent_scans),

                    style =
                        MaterialTheme.typography.headlineSmall,

                    fontWeight = FontWeight.Bold,

                    color = darkGreen
                )


                // ---------------------------------------------
                // VIEW ALL → HISTORY
                // ---------------------------------------------

                Text(
                    text = stringResource(R.string.view_all),

                    style =
                        MaterialTheme.typography.bodyLarge,

                    fontWeight = FontWeight.Bold,

                    color = orange,

                    modifier = Modifier.clickable {
                        onHistoryClick()
                    }
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // =================================================
            // EMPTY RECENT SCANS CARD
            // =================================================

            Surface(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                color = Color.White,

                tonalElevation = 2.dp
            ) {

                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    // -----------------------------------------
                    // LEAF ICON BOX
                    // -----------------------------------------

                    Box(

                        modifier = Modifier
                            .size(72.dp)
                            .clip(
                                RoundedCornerShape(18.dp)
                            )
                            .background(softGreen),

                        contentAlignment =
                            Alignment.Center
                    ) {

                        Text(
                            text = "🌿",

                            style =
                                MaterialTheme.typography.headlineMedium
                        )
                    }


                    Spacer(
                        modifier = Modifier.size(16.dp)
                    )


                    // -----------------------------------------
                    // EMPTY STATE TEXT
                    // -----------------------------------------

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = stringResource(R.string.no_recent_scans),

                            style =
                                MaterialTheme.typography.titleMedium,

                            fontWeight = FontWeight.Bold,

                            color = darkGreen
                        )


                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )


                        Text(
                            text = stringResource(R.string.no_recent_scans_desc),

                            style =
                                MaterialTheme.typography.bodyMedium,

                            color = grayText
                        )
                    }
                }
            }


            // =================================================
            // BOTTOM SPACE
            // =================================================

            Spacer(
                modifier = Modifier.height(24.dp)
            )
        }
    }
}
