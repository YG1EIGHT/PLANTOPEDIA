package com.example.plantopedia

import android.content.Context
import android.content.SharedPreferences

data class UserProfile(
    val name: String,
    val email: String,
    val language: String // "en", "hi", "mr"
)

object UserManager {
    private const val PREFS_NAME = "agroshield_user_prefs"
    private const val KEY_IS_LOGGED_IN = "is_logged_in"
    private const val KEY_NAME = "user_name"
    private const val KEY_EMAIL = "user_email"
    private const val KEY_PASSWORD = "user_password"
    private const val KEY_LANGUAGE = "user_language"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun registerUser(
        context: Context,
        name: String,
        email: String,
        pass: String,
        language: String
    ): Boolean {
        val prefs = getPrefs(context)
        prefs.edit()
            .putString(KEY_NAME, name)
            .putString(KEY_EMAIL, email.lowercase().trim())
            .putString(KEY_PASSWORD, pass)
            .putString(KEY_LANGUAGE, language)
            .putBoolean(KEY_IS_LOGGED_IN, true)
            .apply()
        return true
    }

    fun loginUser(
        context: Context,
        email: String,
        pass: String
    ): Boolean {
        val prefs = getPrefs(context)
        val storedEmail = prefs.getString(KEY_EMAIL, null)
        val storedPass = prefs.getString(KEY_PASSWORD, null)

        if (storedEmail != null && storedEmail == email.lowercase().trim() && storedPass == pass) {
            prefs.edit().putBoolean(KEY_IS_LOGGED_IN, true).apply()
            return true
        }
        return false
    }

    fun logoutUser(context: Context) {
        getPrefs(context).edit().putBoolean(KEY_IS_LOGGED_IN, false).apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        return getPrefs(context).getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUserProfile(context: Context): UserProfile? {
        val prefs = getPrefs(context)
        val name = prefs.getString(KEY_NAME, null) ?: return null
        val email = prefs.getString(KEY_EMAIL, "") ?: ""
        val language = prefs.getString(KEY_LANGUAGE, "en") ?: "en"
        return UserProfile(name = name, email = email, language = language)
    }

    fun saveLanguagePreference(context: Context, language: String) {
        getPrefs(context).edit().putString(KEY_LANGUAGE, language).apply()
    }

    fun getLanguagePreference(context: Context): String {
        return getPrefs(context).getString(KEY_LANGUAGE, "en") ?: "en"
    }
}
