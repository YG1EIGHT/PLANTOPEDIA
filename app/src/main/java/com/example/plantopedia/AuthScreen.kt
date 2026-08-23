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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    onAuthSuccess: () -> Unit,
    onLanguageChanged: (String) -> Unit
) {
    val context = LocalContext.current

    val backgroundColor = Color(0xFFF8F4EC)
    val darkGreen = Color(0xFF174F3D)
    val orange = Color(0xFFCC7040)
    val lightGreen = Color(0xFFE7F0E8)
    val grayText = Color(0xFF777777)

    var isRegisterMode by remember {
        mutableStateOf(!UserManager.isRegistered(context))
    }

    var username by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var selectedLangCode by remember {
        mutableStateOf(UserManager.getLanguage(context))
    }

    var errorMessage by remember { mutableStateOf<String?>(null) }

    val languages = listOf(
        Triple("en", "English", "English"),
        Triple("hi", "हिंदी", "Hindi"),
        Triple("mr", "मराठी", "Marathi")
    )

    var expandedLangDropdown by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = backgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(20.dp))

            // App Logo Icon
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .background(darkGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "🌿",
                    style = MaterialTheme.typography.headlineLarge
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // App Branding Name
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = darkGreen
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Auth Screen Title & Subtitle
            Text(
                text = if (isRegisterMode) {
                    stringResource(id = R.string.register_title)
                } else {
                    stringResource(id = R.string.login_title)
                },
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = darkGreen
            )

            Text(
                text = if (isRegisterMode) {
                    stringResource(id = R.string.register_subtitle)
                } else {
                    stringResource(id = R.string.login_subtitle)
                },
                style = MaterialTheme.typography.bodyMedium,
                color = grayText
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Main Auth Form Card
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                color = Color.White,
                tonalElevation = 2.dp
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {

                    if (isRegisterMode) {
                        // 1. Preferred Language Field
                        Text(
                            text = stringResource(id = R.string.preferred_language),
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = darkGreen
                        )

                        ExposedDropdownMenuBox(
                            expanded = expandedLangDropdown,
                            onExpandedChange = { expandedLangDropdown = !expandedLangDropdown },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            val currentLangTriple = languages.find { it.first == selectedLangCode } ?: languages[0]
                            OutlinedTextField(
                                value = currentLangTriple.second,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.select_language)) },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedLangDropdown)
                                },
                                colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(),
                                shape = RoundedCornerShape(16.dp),
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth()
                            )

                            ExposedDropdownMenu(
                                expanded = expandedLangDropdown,
                                onDismissRequest = { expandedLangDropdown = false }
                            ) {
                                languages.forEach { langTriple ->
                                    DropdownMenuItem(
                                        text = {
                                            Text(
                                                text = langTriple.second,
                                                fontWeight = if (langTriple.first == selectedLangCode) FontWeight.Bold else FontWeight.Normal
                                            )
                                        },
                                        onClick = {
                                            selectedLangCode = langTriple.first
                                            expandedLangDropdown = false
                                            UserManager.setLanguage(context, langTriple.first)
                                            onLanguageChanged(langTriple.first)
                                        }
                                    )
                                }
                            }
                        }

                        // 2. Username Field
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                                errorMessage = null
                            },
                            label = { Text(stringResource(id = R.string.username)) },
                            singleLine = true,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    // 3. Mobile Number Field
                    OutlinedTextField(
                        value = mobile,
                        onValueChange = {
                            mobile = it
                            errorMessage = null
                        },
                        label = { Text(stringResource(id = R.string.mobile_number)) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // 4. Password Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = null
                        },
                        label = { Text(stringResource(id = R.string.password)) },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        trailingIcon = {
                            Text(
                                text = if (passwordVisible) "👁" else "🙈",
                                modifier = Modifier
                                    .clickable { passwordVisible = !passwordVisible }
                                    .padding(8.dp)
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Error message display
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage!!,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Primary Button (Register / Login)
                    Button(
                        onClick = {
                            if (isRegisterMode) {
                                if (username.isBlank()) {
                                    errorMessage = context.getString(R.string.error_username_required)
                                    return@Button
                                }
                                if (!UserManager.isValidMobile(mobile)) {
                                    errorMessage = context.getString(R.string.error_invalid_mobile)
                                    return@Button
                                }
                                if (password.isBlank()) {
                                    errorMessage = context.getString(R.string.error_password_required)
                                    return@Button
                                }

                                val success = UserManager.register(
                                    context = context,
                                    username = username,
                                    mobile = mobile,
                                    password = password,
                                    languageCode = selectedLangCode
                                )
                                if (success) {
                                    onLanguageChanged(selectedLangCode)
                                    onAuthSuccess()
                                } else {
                                    errorMessage = context.getString(R.string.error_user_exists)
                                }
                            } else {
                                if (mobile.isBlank()) {
                                    errorMessage = context.getString(R.string.error_invalid_mobile)
                                    return@Button
                                }
                                if (password.isBlank()) {
                                    errorMessage = context.getString(R.string.error_password_required)
                                    return@Button
                                }

                                val success = UserManager.login(
                                    context = context,
                                    mobileOrUsername = mobile,
                                    password = password
                                )
                                if (success) {
                                    val userLang = UserManager.getLanguage(context)
                                    onLanguageChanged(userLang)
                                    onAuthSuccess()
                                } else {
                                    errorMessage = context.getString(R.string.error_invalid_credentials)
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = orange,
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = if (isRegisterMode) {
                                stringResource(id = R.string.register_button)
                            } else {
                                stringResource(id = R.string.login_button)
                            },
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Mode Toggle Link (Register <-> Login)
                    Text(
                        text = if (isRegisterMode) {
                            stringResource(id = R.string.already_have_account)
                        } else {
                            stringResource(id = R.string.dont_have_account)
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        color = darkGreen,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                isRegisterMode = !isRegisterMode
                                errorMessage = null
                            }
                            .padding(vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Continue as Guest Option
            OutlinedButton(
                onClick = {
                    UserManager.setGuestMode(context, true)
                    onAuthSuccess()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = darkGreen
                )
            ) {
                Text(
                    text = stringResource(id = R.string.continue_as_guest),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}