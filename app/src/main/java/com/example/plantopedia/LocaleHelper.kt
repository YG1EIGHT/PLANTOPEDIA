package com.example.plantopedia

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

object LocaleHelper {

    fun setLocale(context: Context, languageCode: String): Context {
        val locale = Locale.forLanguageTag(languageCode)
        Locale.setDefault(locale)

        val config = Configuration(context.resources.configuration)
        config.setLocale(locale)

        return context.createConfigurationContext(config)
    }

    fun getLanguageDisplayString(languageCode: String): String {
        return when (languageCode) {
            "hi" -> "हिंदी"
            "mr" -> "मराठी"
            else -> "English"
        }
    }
}
