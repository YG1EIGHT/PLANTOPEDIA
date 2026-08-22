package com.example.plantopedia

import android.content.Context

data class DiseaseInfo(
    val crop: String,
    val disease: String,
    val symptoms: String,
    val treatment: String,
    val prevention: String
)

data class DiseaseInfoRes(
    val cropResId: Int,
    val diseaseResId: Int,
    val symptomsResId: Int,
    val treatmentResId: Int,
    val preventionResId: Int
)

object DiseaseDatabase {

    private val dataRes = mapOf(

        // =========================
        // APPLE
        // =========================

        "Apple___Apple_scab" to DiseaseInfoRes(
            R.string.crop_apple,
            R.string.disease_apple_scab,
            R.string.apple_scab_symptoms,
            R.string.apple_scab_treatment,
            R.string.apple_scab_prevention
        ),

        "Apple___Black_rot" to DiseaseInfoRes(
            R.string.crop_apple,
            R.string.disease_black_rot,
            R.string.apple_black_rot_symptoms,
            R.string.apple_black_rot_treatment,
            R.string.apple_black_rot_prevention
        ),

        "Apple___Cedar_apple_rust" to DiseaseInfoRes(
            R.string.crop_apple,
            R.string.disease_cedar_apple_rust,
            R.string.apple_cedar_rust_symptoms,
            R.string.apple_cedar_rust_treatment,
            R.string.apple_cedar_rust_prevention
        ),

        "Apple___healthy" to DiseaseInfoRes(
            R.string.crop_apple,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        ),


        // =========================
        // CORN
        // =========================

        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot" to DiseaseInfoRes(
            R.string.crop_corn,
            R.string.disease_cercospora_leaf_spot,
            R.string.corn_gray_spot_symptoms,
            R.string.corn_gray_spot_treatment,
            R.string.corn_gray_spot_prevention
        ),

        "Corn_(maize)___Common_rust" to DiseaseInfoRes(
            R.string.crop_corn,
            R.string.disease_common_rust,
            R.string.corn_rust_symptoms,
            R.string.corn_rust_treatment,
            R.string.corn_rust_prevention
        ),

        "Corn_(maize)___Northern_Leaf_Blight" to DiseaseInfoRes(
            R.string.crop_corn,
            R.string.disease_northern_leaf_blight,
            R.string.corn_blight_symptoms,
            R.string.corn_blight_treatment,
            R.string.corn_blight_prevention
        ),

        "Corn_(maize)___healthy" to DiseaseInfoRes(
            R.string.crop_corn,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        ),


        // =========================
        // GRAPE
        // =========================

        "Grape___Black_rot" to DiseaseInfoRes(
            R.string.crop_grape,
            R.string.disease_black_rot,
            R.string.grape_black_rot_symptoms,
            R.string.grape_black_rot_treatment,
            R.string.grape_black_rot_prevention
        ),

        "Grape___Esca_(Black_Measles)" to DiseaseInfoRes(
            R.string.crop_grape,
            R.string.disease_esca,
            R.string.grape_esca_symptoms,
            R.string.grape_esca_treatment,
            R.string.grape_esca_prevention
        ),

        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)" to DiseaseInfoRes(
            R.string.crop_grape,
            R.string.disease_leaf_blight,
            R.string.grape_leaf_blight_symptoms,
            R.string.grape_leaf_blight_treatment,
            R.string.grape_leaf_blight_prevention
        ),

        "Grape___healthy" to DiseaseInfoRes(
            R.string.crop_grape,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        ),


        // =========================
        // PEPPER
        // =========================

        "Pepper,_bell___Bacterial_spot" to DiseaseInfoRes(
            R.string.crop_bell_pepper,
            R.string.disease_bacterial_spot,
            R.string.pepper_bacterial_spot_symptoms,
            R.string.pepper_bacterial_spot_treatment,
            R.string.pepper_bacterial_spot_prevention
        ),

        "Pepper,_bell___healthy" to DiseaseInfoRes(
            R.string.crop_bell_pepper,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        ),


        // =========================
        // POTATO
        // =========================

        "Potato___Early_blight" to DiseaseInfoRes(
            R.string.crop_potato,
            R.string.disease_early_blight,
            R.string.potato_early_blight_symptoms,
            R.string.potato_early_blight_treatment,
            R.string.potato_early_blight_prevention
        ),

        "Potato___Late_blight" to DiseaseInfoRes(
            R.string.crop_potato,
            R.string.disease_late_blight,
            R.string.potato_late_blight_symptoms,
            R.string.potato_late_blight_treatment,
            R.string.potato_late_blight_prevention
        ),

        "Potato___healthy" to DiseaseInfoRes(
            R.string.crop_potato,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        ),


        // =========================
        // TOMATO
        // =========================

        "Tomato___Bacterial_spot" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_bacterial_spot,
            R.string.tomato_bacterial_spot_symptoms,
            R.string.tomato_bacterial_spot_treatment,
            R.string.tomato_bacterial_spot_prevention
        ),

        "Tomato___Early_blight" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_early_blight,
            R.string.tomato_early_blight_symptoms,
            R.string.tomato_early_blight_treatment,
            R.string.tomato_early_blight_prevention
        ),

        "Tomato___Late_blight" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_late_blight,
            R.string.tomato_late_blight_symptoms,
            R.string.tomato_late_blight_treatment,
            R.string.tomato_late_blight_prevention
        ),

        "Tomato___Leaf_Mold" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_leaf_mold,
            R.string.tomato_leaf_mold_symptoms,
            R.string.tomato_leaf_mold_treatment,
            R.string.tomato_leaf_mold_prevention
        ),

        "Tomato___Septoria_leaf_spot" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_septoria_leaf_spot,
            R.string.tomato_septoria_symptoms,
            R.string.tomato_septoria_treatment,
            R.string.tomato_septoria_prevention
        ),

        "Tomato___Spider_mites Two-spotted_spider_mite" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_two_spotted_spider_mite,
            R.string.tomato_spider_mites_symptoms,
            R.string.tomato_spider_mites_treatment,
            R.string.tomato_spider_mites_prevention
        ),

        "Tomato___Target_Spot" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_target_spot,
            R.string.tomato_target_spot_symptoms,
            R.string.tomato_target_spot_treatment,
            R.string.tomato_target_spot_prevention
        ),

        "Tomato___Tomato_Yellow_Leaf_Curl_Virus" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_yellow_leaf_curl_virus,
            R.string.tomato_curl_virus_symptoms,
            R.string.tomato_curl_virus_treatment,
            R.string.tomato_curl_virus_prevention
        ),

        "Tomato___Tomato_mosaic_virus" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_mosaic_virus,
            R.string.tomato_mosaic_virus_symptoms,
            R.string.tomato_mosaic_virus_treatment,
            R.string.tomato_mosaic_virus_prevention
        ),

        "Tomato___healthy" to DiseaseInfoRes(
            R.string.crop_tomato,
            R.string.disease_healthy,
            R.string.healthy_symptoms,
            R.string.healthy_treatment,
            R.string.healthy_prevention
        )
    )

    fun get(context: Context, label: String): DiseaseInfo? {
        val res = dataRes[label] ?: return null
        return DiseaseInfo(
            crop = context.getString(res.cropResId),
            disease = context.getString(res.diseaseResId),
            symptoms = context.getString(res.symptomsResId),
            treatment = context.getString(res.treatmentResId),
            prevention = context.getString(res.preventionResId)
        )
    }

    fun get(label: String): DiseaseInfo? {
        val res = dataRes[label] ?: return null
        return DiseaseInfo(
            crop = res.cropResId.toString(),
            disease = res.diseaseResId.toString(),
            symptoms = res.symptomsResId.toString(),
            treatment = res.treatmentResId.toString(),
            prevention = res.preventionResId.toString()
        )
    }
}
