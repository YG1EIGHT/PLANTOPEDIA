package com.example.plantopedia

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject

data class ScanHistoryItem(
    val crop: String,
    val disease: String,
    val confidence: Float,
    val timestamp: Long
)

object ScanHistory {

    private const val PREFS_NAME = "plantopedia_history"

    private fun getHistoryKey(context: Context): String {
        return if (UserManager.isGuest(context)) {
            "scans_guest"
        } else {
            "scans_user"
        }
    }

    fun save(
        context: Context,
        prediction: Prediction
    ) {
        val prefsKey = getHistoryKey(context)

        val preferences =
            context.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )

        val existing =
            preferences.getString(
                prefsKey,
                "[]"
            )

        val array =
            JSONArray(existing)

        val item =
            JSONObject().apply {
                put(
                    "crop",
                    prediction.crop ?: "Unknown"
                )

                put(
                    "disease",
                    prediction.label
                )

                put(
                    "confidence",
                    prediction.confidence
                )

                put(
                    "timestamp",
                    System.currentTimeMillis()
                )
            }

        array.put(item)

        preferences.edit()
            .putString(
                prefsKey,
                array.toString()
            )
            .apply()
    }

    fun getAll(
        context: Context
    ): List<ScanHistoryItem> {
        val prefsKey = getHistoryKey(context)

        val preferences =
            context.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )

        val data =
            preferences.getString(
                prefsKey,
                "[]"
            )

        val array =
            JSONArray(data)

        val result =
            mutableListOf<ScanHistoryItem>()

        for (i in 0 until array.length()) {
            val item =
                array.getJSONObject(i)

            result.add(
                ScanHistoryItem(
                    crop =
                        item.optString(
                            "crop",
                            "Unknown"
                        ),

                    disease =
                        item.optString(
                            "disease",
                            "Unknown"
                        ),

                    confidence =
                        item.optDouble(
                            "confidence",
                            0.0
                        ).toFloat(),

                    timestamp =
                        item.optLong(
                            "timestamp",
                            0L
                        )
                )
            )
        }

        return result.reversed()
    }

    fun clear(
        context: Context
    ) {
        val prefsKey = getHistoryKey(context)

        context
            .getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
            )
            .edit()
            .remove(prefsKey)
            .apply()
    }
}