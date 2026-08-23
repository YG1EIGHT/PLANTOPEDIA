package com.example.plantopedia

import android.content.Context

data class DiseaseInfo(
    val crop: String,
    val disease: String,
    val symptoms: String,
    val treatment: String,
    val prevention: String
)

object DiseaseDatabase {

    private val dataEn = mapOf(
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

    private val dataHi = mapOf(
        "Apple___Apple_scab" to DiseaseInfo(
            "सेब",
            "सेब का स्कैब (Apple Scab)",
            "पत्तियों और फलों पर जैतून-हरे से भूरे रंग के धब्बे दिखाई दे सकते हैं। गंभीर संक्रमण से पत्तियां गिर सकती हैं।",
            "संक्रमित गिरी हुई पत्तियों और फलों को हटाएं। हवा का प्रवाह सुधारें। अनुमोदित कवकनाशी का प्रयोग करें।",
            "गिरी हुई पत्तियों को हटाएं, अच्छी हवा के लिए छंटाई करें और पत्तियों को अधिक समय तक गीला न रहने दें।"
        ),
        "Apple___Black_rot" to DiseaseInfo(
            "सेब",
            "काला सड़न (Black Rot)",
            "पत्तियों पर भूरे गोल धब्बे और फलों पर काले घाव हो सकते हैं। फल काले और झुर्रीदार हो जाते हैं।",
            "संक्रमित फल और मृत सामग्री को हटाएं। प्रभावित शाखाओं की छंटाई करें। अनुमोदित कवकनाशी का उपयोग करें।",
            "बाग की स्वच्छता बनाए रखें और सूखी लकड़ी को हटा दें।"
        ),
        "Apple___Cedar_apple_rust" to DiseaseInfo(
            "सेब",
            "सीडर एप्पल रस्ट (Cedar Apple Rust)",
            "पत्तियों पर पीले-नारंगी धब्बे बन सकते हैं। बाद में पत्तियों के निचले हिस्से में काली संरचनाएं दिखाई दे सकती हैं।",
            "संक्रमित सामग्री को हटाएं। स्थानीय कृषि सिफारिशों के अनुसार अनुमोदित कवकनाशी का उपयोग करें।",
            "अच्छा वायु प्रवाह बनाए रखें और अनुकूल परिस्थितियों में निगरानी करें।"
        ),
        "Apple___healthy" to DiseaseInfo(
            "सेब",
            "स्वस्थ (Healthy)",
            "छवि में कोई प्रमुख बीमारी के लक्षण नहीं पाए गए।",
            "इस छवि से किसी उपचार की आवश्यकता नहीं है।",
            "नियमित निगरानी, संतुलित पोषण, उचित सिंचाई और बाग की स्वच्छता बनाए रखें।"
        ),
        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot" to DiseaseInfo(
            "मक्का",
            "सर्कोस्पोरा लीफ स्पॉट / ग्रे लीफ स्पॉट",
            "मक्के की पत्तियों पर लंबे धूसर या भूरे रंग के घाव विकसित हो सकते हैं।",
            "फसल के मलबे को हटाएं और प्रतिरोधी किस्मों का उपयोग करें। उचित कवकनाशी का प्रयोग करें।",
            "फसल चक्र अपनाएं, प्रतिरोधी किस्मों का उपयोग करें और खेत की सफाई रखें।"
        ),
        "Corn_(maize)___Common_rust" to DiseaseInfo(
            "मक्का",
            "सामान्य रस्ट (Common Rust)",
            "मक्के की पत्तियों पर लाल-भूरे रंग के छोटे-छोटे रस्ट जैसे चकत्ते बन जाते हैं।",
            "प्रतिरोधी किस्मों का उपयोग करें और बीमारी के विकास की निगरानी करें।",
            "प्रतिरोधी हाइब्रिड का उपयोग करें और अच्छा फसल प्रबंधन बनाए रखें।"
        ),
        "Corn_(maize)___Northern_Leaf_Blight" to DiseaseInfo(
            "मक्का",
            "उत्तरी पत्ती झुलसा (Northern Leaf Blight)",
            "पत्तियों पर बड़े लंबे धूसर-हरे से भूरे रंग के घाव विकसित हो सकते हैं।",
            "प्रतिरोधी किस्मों का उपयोग करें। यदि आवश्यक हो तो स्थानीय कृषि सलाह अनुसार कवकनाशी का प्रयोग करें।",
            "फसल चक्र अपनाएं और खेत की स्वच्छता बनाए रखें।"
        ),
        "Corn_(maize)___healthy" to DiseaseInfo(
            "मक्का",
            "स्वस्थ (Healthy)",
            "छवि में कोई मुख्य बीमारी के लक्षण नहीं पाए गए।",
            "उपचार की आवश्यकता नहीं है।",
            "उचित सिंचाई, पोषण और नियमित निगरानी बनाए रखें।"
        ),
        "Grape___Black_rot" to DiseaseInfo(
            "अंगूर",
            "काला सड़न (Black Rot)",
            "भूरे रंग के गोल पत्ती के घाव हो सकते हैं, जिसके बाद फल काले और झुर्रीदार हो जाते हैं।",
            "संक्रमित फल और पौधे के मलबे को हटाएं। अनुमोदित कवकनाशी का उपयोग करें।",
            "अंगूर के बाग की स्वच्छता और अच्छा प्रबंधन बनाए रखें।"
        ),
        "Grape___Esca_(Black_Measles)" to DiseaseInfo(
            "अंगूर",
            "एस्का / ब्लैक मीसल्स (Esca)",
            "पत्तियों पर विशिष्ट मलिनकिरण या धारियां दिख सकती हैं, और फलों पर काले धब्बे बन सकते हैं।",
            "अत्यधिक प्रभावित पौधे की सामग्री को हटाएं। छंटाई के घावों का प्रबंधन करें।",
            "साफ रोपण सामग्री और अच्छी छंटाई प्रथाओं का उपयोग करें।"
        ),
        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)" to DiseaseInfo(
            "अंगूर",
            "लीफ ब्लाइट (Isariopsis Leaf Spot)",
            "पत्तियों के काले धब्बे बड़े हो सकते हैं और पत्तियों को नुकसान पहुंचा सकते हैं।",
            "प्रभावित मलबे को हटाएं और हवा का प्रवाह सुधारें। आवश्यक होने पर अनुमोदित कवकनाशी का उपयोग करें।",
            "हवा का संचार बनाए रखें और संक्रमित मलबे को हटाएं।"
        ),
        "Grape___healthy" to DiseaseInfo(
            "अंगूर",
            "स्वस्थ (Healthy)",
            "कोई बीमारी नहीं पाई गई।",
            "उपचार की आवश्यकता नहीं है।",
            "संतुलित पोषण, सिंचाई और नियमित स्वच्छता बनाए रखें।"
        ),
        "Pepper,_bell___Bacterial_spot" to DiseaseInfo(
            "शिमला मिर्च",
            "जीवाणु धब्बा (Bacterial Spot)",
            "पत्तियों और फलों पर छोटे काले या जल-शोषित धब्बे दिखाई दे सकते हैं।",
            "संक्रमित सामग्री को हटाएं। गीले पौधों को न छुएं। अनुमोदित जीवाणुनाशक का प्रयोग करें।",
            "साफ बीज का उपयोग करें, फसल चक्र अपनाएं और ऊपर से सिंचाई से बचें।"
        ),
        "Pepper,_bell___healthy" to DiseaseInfo(
            "शिमला मिर्च",
            "स्वस्थ (Healthy)",
            "कोई बीमारी नहीं पाई गई।",
            "उपचार की आवश्यकता नहीं है।",
            "संतुलित सिंचाई और पोषण बनाए रखें।"
        ),
        "Potato___Early_blight" to DiseaseInfo(
            "आलू",
            "अगेती झुलसा (Early Blight)",
            "पत्तियों पर संकेंद्रित छल्लेदार गहरे भूरे धब्बे बनते हैं। पुरानी पत्तियां पहले प्रभावित होती हैं।",
            "संक्रमित मलबे को हटाएं। सिफारिश के अनुसार अनुमोदित कवकनाशी का उपयोग करें।",
            "फसल चक्र अपनाएं और पत्तियों को अधिक समय तक गीला न रहने दें।"
        ),
        "Potato___Late_blight" to DiseaseInfo(
            "आलू",
            "पछेती झुलसा (Late Blight)",
            "पत्तियों पर तेजी से अनियमित काले घाव बन सकते हैं। नमी में सफेद फफूंद दिख सकती है।",
            "प्रभावित मलबे को तुरंत हटाएं। पछेती झुलसा कवकनाशी का उपयोग करें।",
            "स्वस्थ बीज का उपयोग करें और खेत की सफाई रखें।"
        ),
        "Potato___healthy" to DiseaseInfo(
            "आलू",
            "स्वस्थ (Healthy)",
            "कोई बीमारी नहीं पाई गई।",
            "उपचार की आवश्यकता नहीं है।",
            "उचित सिंचाई, पोषण और फसल चक्र बनाए रखें।"
        ),
        "Tomato___Bacterial_spot" to DiseaseInfo(
            "टमाटर",
            "जीवाणु धब्बा (Bacterial Spot)",
            "पत्तियों, तनों और फलों पर छोटे काले धब्बे बन सकते हैं।",
            "प्रभावित सामग्री को हटाएं। गीले पौधों को न छुएं। अनुमोदित जीवाणुनाशक का प्रयोग करें।",
            "साफ बीज और फसल चक्र का उपयोग करें।"
        ),
        "Tomato___Early_blight" to DiseaseInfo(
            "टमाटर",
            "अगेती झुलसा (Early Blight)",
            "पुरानी पत्तियों पर गोल भूरे धब्बे बनते हैं जिनके चारों ओर पीलापन आ जाता है।",
            "प्रभावित पत्तियों को हटाएं और अनुमोदित कवकनाशी का उपयोग करें।",
            "फसल चक्र और पौधों के बीच उचित दूरी बनाए रखें।"
        ),
        "Tomato___Late_blight" to DiseaseInfo(
            "टमाटर",
            "पछेती झुलसा (Late Blight)",
            "पत्तियों और तनों पर काले अनियमित घाव बनते हैं।",
            "प्रभावित भाग को तुरंत हटाएं और अनुमोदित झुलसा कवकनाशी का उपयोग करें।",
            "स्वस्थ पौधे लगाएं और हवा का संचार बढ़ाएं।"
        ),
        "Tomato___Leaf_Mold" to DiseaseInfo(
            "टमाटर",
            "लीफ मोल्ड (Leaf Mold)",
            "पत्ती के ऊपरी हिस्से पर पीले धब्बे और निचले हिस्से पर जैतूनी फफूंद दिखाई देती है।",
            "वेंटीलेशन में सुधार करें और फफूंदनाशक का उपयोग करें।",
            "हवा का प्रवाह बढ़ाएं और नमी कम रखें।"
        ),
        "Tomato___Septoria_leaf_spot" to DiseaseInfo(
            "टमाटर",
            "सेप्टोरिया लीफ स्पॉट",
            "पत्तियों पर छोटे गोल धब्बे बनते हैं जिनका किनारा गहरा होता है।",
            "प्रभावित पत्तियों को हटाएं और कवकनाशी का उपयोग करें।",
            "फसल चक्र और सफाई बनाए रखें।"
        ),
        "Tomato___Spider_mites Two-spotted_spider_mite" to DiseaseInfo(
            "टमाटर",
            "मकड़ी का घुन (Two-Spotted Spider Mite)",
            "पत्तियों पर पीलापन, बारीक धब्बे और जाले दिखाई दे सकते हैं।",
            "पत्तियों के निचले हिस्से की जांच करें और अनुमोदित कीटनाशक का प्रयोग करें।",
            "पौधों का तनाव कम रखें और नियमित निगरानी करें।"
        ),
        "Tomato___Target_Spot" to DiseaseInfo(
            "टमाटर",
            "टारगेट स्पॉट (Target Spot)",
            "पत्तियों और फलों पर गोल भूरे रंग के धब्बे बनते हैं।",
            "संक्रमित मलबे को हटाएं और कवकनाशी का उपयोग करें।",
            "पौधों के बीच उचित दूरी और वायु प्रवाह बनाए रखें।"
        ),
        "Tomato___Tomato_Yellow_Leaf_Curl_Virus" to DiseaseInfo(
            "टमाटर",
            "टमाटर लीफ कर्ल वायरस (TYLCV)",
            "पत्तियां ऊपर की ओर मुड़ जाती हैं, पीली हो जाती हैं और पौधे का विकास रुक जाता है।",
            "वायरस का कोई सीधा इलाज नहीं है। प्रभावित पौधों को उखाड़ दें और सफेद मक्खी का नियंत्रण करें।",
            "स्वस्थ पौधे लगाएं और सफेद मक्खी कीट का नियंत्रण करें।"
        ),
        "Tomato___Tomato_mosaic_virus" to DiseaseInfo(
            "टमाटर",
            "टमाटर मोज़ेक वायरस (ToMV)",
            "पत्तियों पर हरे और हल्के हरे रंग के चितकबरे पैटर्न बनते हैं।",
            "प्रभावित पौधों को निकाल दें और औजारों को साफ रखें।",
            "साफ बीज का उपयोग करें और औजारों को रोगाणुमुक्त करें।"
        ),
        "Tomato___healthy" to DiseaseInfo(
            "टमाटर",
            "स्वस्थ (Healthy)",
            "कोई बीमारी नहीं पाई गई।",
            "उपचार की आवश्यकता नहीं है।",
            "उचित सिंचाई, पोषण और सफाई बनाए रखें।"
        )
    )

    private val dataMr = mapOf(
        "Apple___Apple_scab" to DiseaseInfo(
            "सफरचंद",
            "सफरचंद स्कॅब (Apple Scab)",
            "पानांवर आणि फळांवर हिरवट-किंचित तपकिरी डाग दिसू शकतात. तीव्र संसर्गामुळे पाने गळू शकतात.",
            "संक्रमित गळलेली पाने आणि फळे काढून टाका. हवेचा प्रवाह सुधारा. मंजूर बुरशीनाशकाचा वापर करा.",
            "गळलेली पाने हटवा, चांगल्या हवेसाठी छाटणी करा आणि पाने जास्त वेळ ओली राहू देऊ नका."
        ),
        "Apple___Black_rot" to DiseaseInfo(
            "सफरचंद",
            "काळा कुज (Black Rot)",
            "पानांवर गोल तपकिरी डाग आणि फळांवर काळे चट्टे पडू शकतात. फळे काळी आणि सुरकुतलेली होतात.",
            "संक्रमित फळे आणि सुकलेला भाग काढून टाका. बाधित फांद्यांची छाटणी करा.",
            "बाग स्वच्छ ठेवा आणि सुकलेले लाकूड काढून टाका."
        ),
        "Apple___Cedar_apple_rust" to DiseaseInfo(
            "सफरचंद",
            "सिडार अ‍ॅपल रस्ट (Cedar Apple Rust)",
            "पानांवर पिवळे-केशरी डाग पडू शकतात. नंतर पानांच्या खालच्या बाजूला काळे ठिपके दिसतात.",
            "बाधित भाग काढून टाका. स्थानिक कृषी सल्ल्यानुसार योग्य बुरशीनाशक वापरा.",
            "हवेचा खेळता प्रवाह ठेवा आणि रोपांचे निरीक्षण करा."
        ),
        "Apple___healthy" to DiseaseInfo(
            "सफरचंद",
            "निरोगी (Healthy)",
            "फोटोमध्ये रोगाची कोणतीही लक्षणे आढळली नाहीत.",
            "उपचाराची आवश्यकता नाही.",
            "नियमित पाहणी, संतुलित पोषण आणि योग्य पाणी व्यवस्थापन ठेवा."
        ),
        "Corn_(maize)___Cercospora_leaf_spot Gray_leaf_spot" to DiseaseInfo(
            "मका",
            "सर्कोस्पोरा लीफ स्पॉट / राखाडी पानांवरील डाग",
            "मक्याच्या पानांवर लांब राखाडी किंवा तपकिरी डाग दिसू शकतात.",
            "पिकाचा कचरा हटवा आणि रोगप्रतिकारक वाण वापरा.",
            "पीक पालट करा आणि शेत स्वच्छ ठेवा."
        ),
        "Corn_(maize)___Common_rust" to DiseaseInfo(
            "मका",
            "सामान्य तांबेरा (Common Rust)",
            "पानांवर लहान लालसर-तपकिरी रंगाचे तांबेऱ्याचे ठिपके येतात.",
            "प्रतिकारक वाण वापरा आणि रोगाच्या वाढीवर लक्ष ठेवा.",
            "प्रतिकारक संकरित वाण वापरा आणि पीक व्यवस्थापन ठेवा."
        ),
        "Corn_(maize)___Northern_Leaf_Blight" to DiseaseInfo(
            "मका",
            "उत्तर पर्ण करपा (Northern Leaf Blight)",
            "पानांवर मोठे लांबट राखाडी-हिरवे डाग पडू शकतात.",
            "प्रतिकारक वाण वापरा आणि गरजेनुसार बुरशीनाशक फवारा.",
            "पीक पालट करा आणि शेत स्वच्छ ठेवा."
        ),
        "Corn_(maize)___healthy" to DiseaseInfo(
            "मका",
            "निरोगी (Healthy)",
            "कोणतीही लक्षणे आढळली नाहीत.",
            "उपचाराची गरज नाही.",
            "योग्य पाणी आणि पोषण व्यवस्थापन ठेवा."
        ),
        "Grape___Black_rot" to DiseaseInfo(
            "द्राक्षे",
            "काळा कुज (Black Rot)",
            "पानांवर गोल तपकिरी डाग पडतात आणि फळे काळी पडतात.",
            "संक्रमित फळे आणि कचरा काढून टाका. हवेचा खेळता प्रवाह वाढवा.",
            "द्राक्ष बाग स्वच्छ ठेवा."
        ),
        "Grape___Esca_(Black_Measles)" to DiseaseInfo(
            "द्राक्षे",
            "एस्का / ब्लॅक मीसल्स (Esca)",
            "पानांवर पट्टे आणि फळांवर काळे डाग दिसू शकतात.",
            "गंभीर बाधित भाग काढून टाका. छाटणीच्या जखमांचे व्यवस्थापन करा.",
            "निरोगी रोपे आणि योग्य छाटणी पद्धती वापरा."
        ),
        "Grape___Leaf_blight_(Isariopsis_Leaf_Spot)" to DiseaseInfo(
            "द्राक्षे",
            "पानांवरील करपा (Isariopsis Leaf Spot)",
            "पानांवरील काळे डाग वाढून पानांचे नुकसान करतात.",
            "बाधित कचरा हटवा आणि हवेचा प्रवाह सुधारा.",
            "बागेत हवा खेळती ठेवा."
        ),
        "Grape___healthy" to DiseaseInfo(
            "द्राक्षे",
            "निरोगी (Healthy)",
            "कोणताही रोग आढळला नाही.",
            "उपचाराची गरज नाही.",
            "संतुलित पाणी आणि खत व्यवस्थापन ठेवा."
        ),
        "Pepper,_bell___Bacterial_spot" to DiseaseInfo(
            "ढोबळी मिरची",
            "जीवाणूजन्य डाग (Bacterial Spot)",
            "पानांवर आणि फळांवर लहान काळे किंवा पाण्यासारखे डाग दिसतात.",
            "संक्रमित भाग काढून टाका. ओल्या रोपांना हात लावू नका.",
            "स्वच्छ बियाणे आणि पीक पालट वापरा."
        ),
        "Pepper,_bell___healthy" to DiseaseInfo(
            "ढोबळी मिरची",
            "निरोगी (Healthy)",
            "कोणताही रोग आढळला नाही.",
            "उपचाराची गरज नाही.",
            "नियमित काळजी आणि पाणी व्यवस्थापन ठेवा."
        ),
        "Potato___Early_blight" to DiseaseInfo(
            "बटाटा",
            "लवकर येणारा करपा (Early Blight)",
            "पानांवर गोल तपकिरी वलये असलेले डाग पडतात.",
            "संक्रमित कचरा हटवा आणि बुरशीनाशक वापरा.",
            "पीक पालट करा आणि पाने ओली ठेवू नका."
        ),
        "Potato___Late_blight" to DiseaseInfo(
            "बटाटा",
            "उशिरा येणारा करपा (Late Blight)",
            "पानांवर वेगाने काळे डाग पसरतात.",
            "बाधित भाग त्वरित काढून टाका आणि करपा नाशक फवारा.",
            "निरोगी बियाणे वापरा आणि शेत स्वच्छ ठेवा."
        ),
        "Potato___healthy" to DiseaseInfo(
            "बटाटा",
            "निरोगी (Healthy)",
            "कोणताही रोग आढळला नाही.",
            "उपचाराची गरज नाही.",
            "योग्य खत आणि पाणी व्यवस्थापन ठेवा."
        ),
        "Tomato___Bacterial_spot" to DiseaseInfo(
            "टोमॅटो",
            "जीवाणूजन्य डाग (Bacterial Spot)",
            "पाने, खोड आणि फळांवर लहान काळे डाग येतात.",
            "बाधित भाग काढून टाका आणि जीवाणूनाशक वापरा.",
            "स्वच्छ बियाणे आणि पीक पालट वापरा."
        ),
        "Tomato___Early_blight" to DiseaseInfo(
            "टोमॅटो",
            "लवकर येणारा करपा (Early Blight)",
            "जुन्या पानांवर गोल तपकिरी डाग येतात.",
            "बाधित पाने काढून टाका आणि बुरशीनाशक वापरा.",
            "रोपांमध्ये योग्य अंतर ठेवा."
        ),
        "Tomato___Late_blight" to DiseaseInfo(
            "टोमॅटो",
            "उशिरा येणारा करपा (Late Blight)",
            "पानांवर आणि खोडावर अनियमित काळे डाग येतात.",
            "बाधित वनस्पती भाग त्वरित काढून टाका आणि योग्य फवारणी करा.",
            "हवेचा प्रवाह सुधारा आणि शेत स्वच्छ ठेवा."
        ),
        "Tomato___Leaf_Mold" to DiseaseInfo(
            "टोमॅटो",
            "लीफ मोल्ड (Leaf Mold)",
            "पानाच्या वरच्या भागावर पिवळे डाग आणि खाली बुरशी दिसते.",
            "हवेचा खेळता प्रवाह वाढवा आणि बुरशीनाशक वापरा.",
            "नमी कमी ठेवा आणि स्वच्छता राखा."
        ),
        "Tomato___Septoria_leaf_spot" to DiseaseInfo(
            "टोमॅटो",
            "सेप्टोरिया पानांवरील डाग",
            "पानांवर लहान गोल डाग पडतात ज्यांची कडा काळी असते.",
            "बाधित पाने काढून टाका आणि फवारणी करा.",
            "पीक पालट आणि स्वच्छता राखा."
        ),
        "Tomato___Spider_mites Two-spotted_spider_mite" to DiseaseInfo(
            "टोमॅटो",
            "लाल कोळी (Two-Spotted Spider Mite)",
            "पानांवर पिवळे ठिपके आणि जाळी दिसू शकते.",
            "पानांच्या खालच्या बाजूची पाहणी करा आणि कीटकनाशक वापरा.",
            "नियमित पाहणी करा आणि रोपांचे रक्षण करा."
        ),
        "Tomato___Target_Spot" to DiseaseInfo(
            "टोमॅटो",
            "टारगेट स्पॉट (Target Spot)",
            "पानांवर गोल तपकिरी वलयाकार डाग पडतात.",
            "कचरा हटवा आणि बुरशीनाशक वापरा.",
            "योग्य अंतर आणि हवा खेळती ठेवा."
        ),
        "Tomato___Tomato_Yellow_Leaf_Curl_Virus" to DiseaseInfo(
            "टोमॅटो",
            "टोमॅटो पर्णगुच्छ व्हायरस (TYLCV)",
            "पाने वरच्या बाजूला वळतात, पिवळी पडतात आणि वाढ खुंटते.",
            "व्हायरसवर थेट औषध नाही. बाधित रोपे उपटून टाका आणि पांढरी माशी नियंत्रित करा.",
            "निरोगी रोपे वापरा आणि कीटक नियंत्रण करा."
        ),
        "Tomato___Tomato_mosaic_virus" to DiseaseInfo(
            "टोमॅटो",
            "टोमॅटो मोझॅक व्हायरस (ToMV)",
            "पानांवर हिरवे आणि पिवळसर चट्टे दिसतात.",
            "बाधित रोपे नष्ट करा आणि अवजारे स्वच्छ ठेवा.",
            "स्वच्छ बियाणे आणि निर्जंतुक अवजारे वापरा."
        ),
        "Tomato___healthy" to DiseaseInfo(
            "टोमॅटो",
            "निरोगी (Healthy)",
            "कोणताही रोग आढळला नाही.",
            "उपचाराची गरज नाही.",
            "योग्य काळजी आणि पाणी व्यवस्थापन चालू ठेवा."
        )
    )

    fun get(label: String, context: Context? = null): DiseaseInfo? {
        val lang = context?.let { UserManager.getLanguage(it) } ?: "en"
        return when (lang) {
            "hi" -> dataHi[label] ?: dataEn[label]
            "mr" -> dataMr[label] ?: dataEn[label]
            else -> dataEn[label]
        }
    }
}