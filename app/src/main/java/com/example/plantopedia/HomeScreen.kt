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
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
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
fun HomeScreen(
    cameraPermissionGranted: Boolean,
    onScanClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onLanguageChanged: (String) -> Unit,
    onLogout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val lightCard = Color(0xFFEDE5D6)
    val softGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)

    var showMenu by remember { mutableStateOf(false) }

    val username = if (UserManager.isGuest(context)) {
        stringResource(id = R.string.guest_user)
    } else {
        UserManager.getUsername(context)
    }

    Surface(
        modifier = modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 18.dp,
                    bottom = 24.dp
                )
        ) {
            // HEADER & MENU
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.welcome_farmer, username),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = darkGreen
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = stringResource(id = R.string.home_subtitle),
                        style = MaterialTheme.typography.bodyLarge,
                        color = grayText
                    )
                }

                Box {
                    Text(
                        text = "🌐",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .clickable { showMenu = true }
                            .padding(8.dp)
                    )

                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("English") },
                            onClick = {
                                showMenu = false
                                UserManager.setLanguage(context, "en")
                                onLanguageChanged("en")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("हिंदी") },
                            onClick = {
                                showMenu = false
                                UserManager.setLanguage(context, "hi")
                                onLanguageChanged("hi")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("मराठी") },
                            onClick = {
                                showMenu = false
                                UserManager.setLanguage(context, "mr")
                                onLanguageChanged("mr")
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(stringResource(id = R.string.logout), color = Color.Red) },
                            onClick = {
                                showMenu = false
                                UserManager.logout(context)
                                onLogout()
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(28.dp))

            // CHECK MY PLANT CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(28.dp))
                    .background(lightCard)
                    .padding(horizontal = 24.dp, vertical = 28.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(RoundedCornerShape(55.dp))
                            .background(darkGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "📷",
                            style = MaterialTheme.typography.displaySmall
                        )
                    }

                    Spacer(modifier = Modifier.height(18.dp))

                    Text(
                        text = stringResource(id = R.string.check_my_plant),
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = darkGreen
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = stringResource(id = R.string.check_plant_desc),
                        style = MaterialTheme.typography.bodyLarge,
                        color = grayText
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // SCAN CROP BUTTON
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
                    text = "📷  ${stringResource(id = R.string.scan_crop)}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // CAMERA STATUS
            Text(
                text = if (cameraPermissionGranted) {
                    stringResource(id = R.string.camera_ready)
                } else {
                    stringResource(id = R.string.camera_permission_required)
                },
                style = MaterialTheme.typography.bodyLarge,
                color = if (cameraPermissionGranted) darkGreen else Color.Red
            )

            Spacer(modifier = Modifier.height(32.dp))

            // RECENT SCANS HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.recent_scans),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = darkGreen
                )

                Text(
                    text = stringResource(id = R.string.view_all),
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = orange,
                    modifier = Modifier.clickable { onHistoryClick() }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // EMPTY RECENT SCANS CARD
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
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(72.dp)
                            .clip(RoundedCornerShape(18.dp))
                            .background(softGreen),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "🌿",
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                    Spacer(modifier = Modifier.size(16.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(id = R.string.no_recent_scans),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = darkGreen
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = stringResource(id = R.string.no_recent_scans_desc),
                            style = MaterialTheme.typography.bodyMedium,
                            color = grayText
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}