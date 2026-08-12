package com.example.plantopedia

data class DiseaseInfo(
    val crop: String,
    val disease: String,
    val symptoms: String,
    val treatment: String,
    val prevention: String
)

object DiseaseDatabase {

    private val data = mapOf(

        // =========================
        // APPLE
        // =========================

        "Apple___Apple_scab" to DiseaseInfo(
            "Apple",
            "Apple Scab",
            "Olive-green to brown spots can appear on leaves and fruit. Severe infection may cause leaf drop and distorted fruit.",
            "Remove infected fallen leaves and fruit. Improve airflow. If treatment is required, use a locally approved fungicide strictly according to its label.",
            "Remove fallen leaves, prune for good airflow, and avoid prolonged leaf wetness."
        ),

        "Apple___Black_rot" to DiseaseInfo(
            "Apple",
            "Black Rot",
            "Brown circular leaf spots and dark lesions may develop on fruit. Fruit can become black and shriveled.",
            "Remove infected fruit and dead plant material. Prune affected branches. Use an approved fungicide according to local recommendations and label directions.",
            "Maintain orchard sanitation and remove mummified fruit and dead wood."
        ),

        "Apple___Cedar_apple_rust" to DiseaseInfo(
            "Apple",
            "Cedar Apple Rust",
            "Yellow-orange spots may develop on leaves. Later, dark structures can appear on the underside of affected leaves.",
            "Remove severely affected material where practical. Use an approved fungicide according to local agricultural recommendations and product-label instructions.",
            "Maintain good airflow and monitor plants during favorable disease conditions."
        ),

        "Apple___healthy" to DiseaseInfo(
            "Apple",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Continue regular monitoring, balanced nutrition, proper irrigation, and good orchard sanitation."
        ),


        // =========================
        // CORN
        // =========================

        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot" to DiseaseInfo(
            "Corn",
            "Cercospora Leaf Spot / Gray Leaf Spot",
            "Long gray, tan, or brown lesions may develop on corn leaves and can expand under favorable conditions.",
            "Remove crop debris where practical and use resistant varieties. If fungicide treatment is appropriate, follow local agricultural recommendations and the product label.",
            "Use crop rotation, resistant varieties, and good field sanitation."
        ),

        "Corn_(maize)___Common_rust" to DiseaseInfo(
            "Corn",
            "Common Rust",
            "Small reddish-brown rust-colored pustules develop on corn leaves.",
            "Use resistant varieties and monitor disease development. Where fungicide treatment is appropriate, follow local recommendations and label directions.",
            "Use resistant hybrids and maintain good crop management."
        ),

        "Corn_(maize)___Northern_Leaf_Blight" to DiseaseInfo(
            "Corn",
            "Northern Leaf Blight",
            "Large elongated gray-green to tan lesions may develop on corn leaves.",
            "Use resistant varieties and manage crop residue. If fungicide treatment is needed, follow local agricultural recommendations and the product label.",
            "Practice crop rotation, use resistant varieties, and maintain field sanitation."
        ),

        "Corn_(maize)___healthy" to DiseaseInfo(
            "Corn",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Maintain proper irrigation, nutrition, weed management, and regular crop monitoring."
        ),


        // =========================
        // GRAPE
        // =========================

        "Grape___Black_rot" to DiseaseInfo(
            "Grape",
            "Black Rot",
            "Brown circular leaf lesions may develop, followed by dark, shriveled fruit.",
            "Remove infected fruit and plant debris. Improve canopy airflow. Use an approved fungicide according to local recommendations and label directions when necessary.",
            "Maintain vineyard sanitation and good canopy management."
        ),

        "Grape___Esca_(Black_Measles)" to DiseaseInfo(
            "Grape",
            "Esca / Black Measles",
            "Leaves may show characteristic discoloration or striping, while fruit can develop dark spots.",
            "Remove severely affected plant material where appropriate. Manage pruning wounds and follow local agricultural guidance for vineyard disease management.",
            "Use clean planting material and good pruning and sanitation practices."
        ),

        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)" to DiseaseInfo(
            "Grape",
            "Leaf Blight / Isariopsis Leaf Spot",
            "Dark leaf spots can enlarge and cause affected foliage to deteriorate.",
            "Remove affected plant debris and improve airflow. Use an approved fungicide according to local recommendations and label instructions if required.",
            "Maintain canopy ventilation and remove infected debris."
        ),

        "Grape___healthy" to DiseaseInfo(
            "Grape",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Maintain balanced nutrition, irrigation, canopy management, and vineyard sanitation."
        ),


        // =========================
        // PEPPER
        // =========================

        "Pepper,_bell___Bacterial_spot" to DiseaseInfo(
            "Bell Pepper",
            "Bacterial Spot",
            "Small dark or water-soaked spots may appear on leaves and fruit.",
            "Remove severely infected material and avoid working with wet plants. Use locally approved bacterial disease management products according to their labels.",
            "Use clean seed or transplants, sanitation, crop rotation, and avoid overhead irrigation."
        ),

        "Pepper,_bell___healthy" to DiseaseInfo(
            "Bell Pepper",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Maintain balanced irrigation, nutrition, sanitation, and regular monitoring."
        ),


        // =========================
        // POTATO
        // =========================

        "Potato___Early_blight" to DiseaseInfo(
            "Potato",
            "Early Blight",
            "Dark brown leaf spots often develop with concentric ring patterns. Older leaves are commonly affected first.",
            "Remove infected plant debris and maintain plant vigor. If treatment is required, use an approved fungicide according to local agricultural recommendations and label instructions.",
            "Practice crop rotation, field sanitation, and avoid prolonged leaf wetness."
        ),

        "Potato___Late_blight" to DiseaseInfo(
            "Potato",
            "Late Blight",
            "Dark irregular lesions can rapidly develop on leaves. Under humid conditions, affected tissue may show pale growth around lesions.",
            "Remove heavily infected material where practical and manage the crop promptly. Use an approved late-blight fungicide according to local agricultural recommendations and the product label.",
            "Use healthy planting material, resistant varieties where available, good field sanitation, and avoid prolonged leaf wetness."
        ),

        "Potato___healthy" to DiseaseInfo(
            "Potato",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Maintain proper irrigation, nutrition, crop rotation, and field sanitation."
        ),


        // =========================
        // TOMATO
        // =========================

        "Tomato___Bacterial_spot" to DiseaseInfo(
            "Tomato",
            "Bacterial Spot",
            "Small dark spots can occur on leaves, stems, and fruit. Severe infection can cause leaf loss.",
            "Remove severely affected plant material and avoid handling plants when wet. Use locally approved bacterial disease management products according to their labels.",
            "Use clean seed or transplants, sanitation, crop rotation, and avoid overhead irrigation."
        ),

        "Tomato___Early_blight" to DiseaseInfo(
            "Tomato",
            "Early Blight",
            "Dark brown spots with concentric rings commonly appear on older leaves. Yellowing may develop around lesions.",
            "Remove affected leaves where practical and maintain plant vigor. Use an approved fungicide according to local recommendations and label instructions when treatment is necessary.",
            "Use crop rotation, sanitation, adequate plant spacing, and avoid prolonged leaf wetness."
        ),

        "Tomato___Late_blight" to DiseaseInfo(
            "Tomato",
            "Late Blight",
            "Dark, irregular lesions can appear on leaves and stems. Under humid conditions, affected areas can develop pale fungal growth.",
            "Remove severely affected plant material where practical and manage the crop promptly. Use a locally approved late-blight fungicide according to its label and local agricultural recommendations.",
            "Use healthy planting material, improve airflow, avoid prolonged leaf wetness, and remove infected debris."
        ),

        "Tomato___Leaf_Mold" to DiseaseInfo(
            "Tomato",
            "Leaf Mold",
            "Yellowish patches may appear on the upper leaf surface with olive or grayish fungal growth on the underside.",
            "Improve ventilation and reduce humidity around foliage. Remove severely affected leaves and use an approved fungicide according to local recommendations when required.",
            "Improve greenhouse ventilation, reduce leaf wetness, and maintain sanitation."
        ),

        "Tomato___Septoria_leaf_spot" to DiseaseInfo(
            "Tomato",
            "Septoria Leaf Spot",
            "Small circular leaf spots often have dark margins and lighter centers. Numerous spots can cause leaf yellowing and drop.",
            "Remove affected leaves and plant debris. Improve airflow and use an approved fungicide according to local recommendations and label instructions when necessary.",
            "Use crop rotation, sanitation, adequate spacing, and avoid overhead irrigation."
        ),

        "Tomato___Spider_mites Two-spotted_spider_mite" to DiseaseInfo(
            "Tomato",
            "Two-Spotted Spider Mite",
            "Fine stippling, yellowing, and possible webbing may appear on leaves. Severe infestation can cause leaf decline.",
            "Inspect the underside of leaves. Use locally approved mite-management methods and follow the product label if a pesticide is required.",
            "Monitor plants regularly, reduce plant stress, and encourage beneficial predators where appropriate."
        ),

        "Tomato___Target_Spot" to DiseaseInfo(
            "Tomato",
            "Target Spot",
            "Brown circular lesions with concentric rings may develop on leaves and fruit.",
            "Remove infected plant debris and improve airflow. Use an approved fungicide according to local agricultural recommendations and label instructions when needed.",
            "Maintain sanitation, spacing, crop rotation, and good canopy ventilation."
        ),

        "Tomato___Tomato_Yellow_Leaf_Curl_Virus" to DiseaseInfo(
            "Tomato",
            "Tomato Yellow Leaf Curl Virus",
            "Leaves may curl upward, become yellow, and show reduced growth. Plants can become severely stunted.",
            "There is no curative treatment for an infected plant. Remove severely affected plants and manage the insect vector according to local agricultural guidance.",
            "Use healthy planting material and monitor and manage whitefly vectors."
        ),

        "Tomato___Tomato_mosaic_virus" to DiseaseInfo(
            "Tomato",
            "Tomato Mosaic Virus",
            "Leaves may show mottled light and dark green patterns, distortion, and reduced plant growth.",
            "There is no curative pesticide treatment for a virus-infected plant. Remove severely infected plants and sanitize hands and tools to reduce spread.",
            "Use clean seed and transplants, sanitize tools, and control mechanical spread."
        ),

        "Tomato___healthy" to DiseaseInfo(
            "Tomato",
            "Healthy",
            "No major disease symptoms were detected in the image.",
            "No disease treatment is indicated from this image.",
            "Continue regular monitoring and maintain proper watering, nutrition, spacing, and sanitation."
        )
    )

    fun get(label: String): DiseaseInfo? {
        return data[label]
    }
}