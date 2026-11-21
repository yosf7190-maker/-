package com.example.data

import com.example.R
import com.example.model.FinishOption
import com.example.model.ProductCategory
import com.example.model.ProductSpec
import com.example.model.ShowroomProduct

object ShowroomCatalogRepository {

    val products: List<ShowroomProduct> = listOf(
        // ==================== مغاسل (Sinks) ====================
        ShowroomProduct(
            id = "sink_carrara_vessel",
            sku = "NR-SNK-101",
            nameAr = "مغسلة كرارا فوق الكاونتر",
            category = ProductCategory.SINKS,
            price = 22_500,
            unitAr = "للقطعة",
            originAr = "إيطاليا",
            shortTagAr = "خزف معالج نانو",
            descriptionAr = "مغسلة حوض بيضاوية بحواف رفيعة جداً (5 ملم) مطلية بطبقة نانو مقاومة للبقع والترسبات الكلسية، تضفي لمسة معمارية هادئة على الحمامات العصرية.",
            imageRes = R.drawable.img_cat_sinks,
            isFeatured = true,
            finishes = listOf(
                FinishOption("أبيض ثلجي لامع", 0xFFF7F7F5),
                FinishOption("بيج ترافرتين مطفي", 0xFFD9CDBA),
                FinishOption("أسود بازلت مطفي", 0xFF282B2D)
            ),
            specs = listOf(
                ProductSpec("الأبعاد", "60 × 40 × 15 سم"),
                ProductSpec("المادة", "خزف فاخر عالي الكثافة"),
                ProductSpec("نوع التركيب", "فوق الرخام / الكاونتر"),
                ProductSpec("الضمان", "10 سنوات على اللون والسطح")
            )
        ),
        ShowroomProduct(
            id = "sink_floating_vanity",
            sku = "NR-SNK-104",
            nameAr = "مغسلة النور المعلقة مع خزانة",
            category = ProductCategory.SINKS,
            price = 52_000,
            unitAr = "للطقم",
            originAr = "تركيا",
            shortTagAr = "مقاوم للرطوبة 100%",
            descriptionAr = "طقم مغسلة معلق بتصميم أضلاع طولية عصرية، مزود بخزانة PVC مقاومة تماماً للماء والرطوبة مع أدراج إغلاق هادئ (Soft-Close) ومرآة بإضاءة مخفية.",
            imageRes = R.drawable.img_hero_showroom,
            isFeatured = true,
            finishes = listOf(
                FinishOption("خشب بلوط طبيعي", 0xFFB8946D),
                FinishOption("رمادي إسمنتي", 0xFF888C8E),
                FinishOption("أبيض رخامي", 0xFFECE9E2)
            ),
            specs = listOf(
                ProductSpec("العرض", "80 × 46 سم"),
                ProductSpec("هيكل الخزانة", "PVC عالي التحمل"),
                ProductSpec("المفصلات", "إغلاق هيدروليكي هادئ"),
                ProductSpec("الضمان", "5 سنوات شامل")
            )
        ),
        ShowroomProduct(
            id = "sink_monolith_pedestal",
            sku = "NR-SNK-109",
            nameAr = "مغسلة عمودية متكاملة أوريون",
            category = ProductCategory.SINKS,
            price = 39_500,
            unitAr = "للقطعة",
            originAr = "إسبانيا",
            shortTagAr = "تصميم قطعة واحدة",
            descriptionAr = "مغسلة عمودية منحوتة بقطعة واحدة متجانسة مناسبة لحمامات الضيوف والصالات المفتوحة، مع تصريف مخفي أنيق.",
            imageRes = R.drawable.img_cat_sinks,
            isFeatured = false,
            finishes = listOf(
                FinishOption("أبيض مطفي", 0xFFF1EFEA),
                FinishOption("رمادي حجري", 0xFF7D807E)
            ),
            specs = listOf(
                ProductSpec("الارتفاع الكلي", "85 سم"),
                ProductSpec("قطر الحوض", "45 سم"),
                ProductSpec("التصريف", "تصريف مخفي مدمج"),
                ProductSpec("الضمان", "10 سنوات")
            )
        ),
        ShowroomProduct(
            id = "sink_double_marble",
            sku = "NR-SNK-115",
            nameAr = "مغسلة حوضين دبل كاونتر",
            category = ProductCategory.SINKS,
            price = 68_000,
            unitAr = "للطقم",
            originAr = "إيطاليا",
            shortTagAr = "مناسب للماستر",
            descriptionAr = "تصميم رحب بحوضين متناظرين من الخزف المعالج مع سطح غرانيت مقاوم للخدش، مثالي لأجنحة النوم الرئيسية.",
            imageRes = R.drawable.img_cat_sinks,
            isFeatured = false,
            finishes = listOf(
                FinishOption("أبيض + ترافرتين", 0xFFE5DEC9),
                FinishOption("أبيض + أسود جالاكسي", 0xFF232629)
            ),
            specs = listOf(
                ProductSpec("الأبعاد", "140 × 50 سم"),
                ProductSpec("عدد الأحواض", "حوضان متماثلان"),
                ProductSpec("فتحة الخلاط", "تناسب الخلاطات الجدارية والسطحية"),
                ProductSpec("الضمان", "7 سنوات")
            )
        ),

        // ==================== خلاطات (Mixers) ====================
        ShowroomProduct(
            id = "mixer_brass_waterfall",
            sku = "NR-MIX-201",
            nameAr = "خلاط مغسلة شلال ذهبي مطفي",
            category = ProductCategory.MIXERS,
            price = 13_500,
            unitAr = "للقطعة",
            originAr = "ألمانيا",
            shortTagAr = "قلب نحاس ألماني",
            descriptionAr = "خلاط مغسلة بتدفق مائي انسيابي هادئ وتشطيب PVD ذهبي شامبين مطفي مقاوم للخدوش وتغير اللون، مزود بخرطوشة سيراميك ألمانية.",
            imageRes = R.drawable.img_cat_mixers,
            isFeatured = true,
            finishes = listOf(
                FinishOption("ذهبي مطفي (PVD)", 0xFFC59D45),
                FinishOption("أسود فحمي مطفي", 0xFF212426),
                FinishOption("كروم لامع", 0xFFC8CED3)
            ),
            specs = listOf(
                ProductSpec("جسم الخلاط", "نحاس صلب خالي من الرصاص"),
                ProductSpec("الخرطوشة", "سيراميك 35 ملم عالية التحمل"),
                ProductSpec("توفير المياه", "فلتر توفير حتى 30%"),
                ProductSpec("الضمان", "7 سنوات ضد التهريب واللون")
            )
        ),
        ShowroomProduct(
            id = "mixer_concealed_shower",
            sku = "NR-MIX-205",
            nameAr = "طقم شاور دفن ثرموستاتيك",
            category = ProductCategory.MIXERS,
            price = 36_000,
            unitAr = "للطقم",
            originAr = "ألمانيا",
            shortTagAr = "تثبيت حرارة ذكي",
            descriptionAr = "نظام دش مخفي بالكامل في الجدار مع منظم حرارة ثرموستاتيك يحافظ على ثبات حرارة الماء، ورأس مطري عريض 30×30 سم.",
            imageRes = R.drawable.img_cat_mixers,
            isFeatured = true,
            finishes = listOf(
                FinishOption("أسود مطفي + ذهبي", 0xFF2D2A24),
                FinishOption("نيكل مصقول", 0xFFAEB3B7),
                FinishOption("برونز دافئ", 0xFF8C6B43)
            ),
            specs = listOf(
                ProductSpec("رأس الدش", "30 × 30 سم ستانلس ستيل 304"),
                ProductSpec("صندوق الدفن", "نحاس + حماية عازلة"),
                ProductSpec("وظائف التدفق", "مطري + سماعة يدوية"),
                ProductSpec("الضمان", "10 سنوات")
            )
        ),
        ShowroomProduct(
            id = "mixer_tall_basin",
            sku = "NR-MIX-210",
            nameAr = "خلاط مغسلة طويل لحوض السطح",
            category = ProductCategory.MIXERS,
            price = 11_800,
            unitAr = "للقطعة",
            originAr = "إيطاليا",
            shortTagAr = "ارتفاع 30 سم",
            descriptionAr = "خلاط بعنق طويل رشيق مصمم خصيصاً للمغاسل الموضوعة فوق الكاونتر، مع مهوية مضادة لتناثر الماء.",
            imageRes = R.drawable.img_cat_mixers,
            isFeatured = false,
            finishes = listOf(
                FinishOption("كروم مرآتي", 0xFFD0D6DB),
                FinishOption("أسود مطفي", 0xFF232527),
                FinishOption("ذهبي مصقول", 0xFFD4AF37)
            ),
            specs = listOf(
                ProductSpec("الارتفاع", "30 سم"),
                ProductSpec("ضغط التشغيل", "1 – 6 بار"),
                ProductSpec("خراطيم التوصيل", "مرنة 50 سم ستانلس"),
                ProductSpec("الضمان", "5 سنوات")
            )
        ),
        ShowroomProduct(
            id = "mixer_kitchen_pullout",
            sku = "NR-MIX-218",
            nameAr = "خلاط مجلى مطبخ سحاب مرن",
            category = ProductCategory.MIXERS,
            price = 15_500,
            unitAr = "للقطعة",
            originAr = "ألمانيا",
            shortTagAr = "وضعيتين للرش",
            descriptionAr = "خلاط مطبخ عملي برأس سحاب قابل للدوران 360 درجة وزر تبديل بين التدفق العادي والرشاش القوي لتنظيف أسهل.",
            imageRes = R.drawable.img_cat_mixers,
            isFeatured = false,
            finishes = listOf(
                FinishOption("ستانلس مطفي", 0xFFA6ACAF),
                FinishOption("أسود تيتانيوم", 0xFF2A2E31)
            ),
            specs = listOf(
                ProductSpec("الدوران", "360 درجة"),
                ProductSpec("طول السحب", "حتى 45 سم"),
                ProductSpec("المادة", "ستانلس ستيل 304 غذائي"),
                ProductSpec("الضمان", "7 سنوات")
            )
        ),

        // ==================== سيراميك (Ceramics) ====================
        ShowroomProduct(
            id = "ceramic_travertine_large",
            sku = "NR-CER-301",
            nameAr = "بورسلان ترافرتين إيطالي 60×120",
            category = ProductCategory.CERAMICS,
            price = 3_200,
            unitAr = "للمتر المربع",
            originAr = "إيطاليا",
            shortTagAr = "قص ليزر",
            descriptionAr = "ألواح بورسلان مقصوصة بالليزر (Rectified) بتفاصيل حجر الترافرتين الطبيعي الدافئ، مناسبة للأرضيات والجدران الداخلية والخارجية.",
            imageRes = R.drawable.img_cat_ceramics,
            isFeatured = true,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("ترافرتين بيج رملي", 0xFFD9CBB8),
                FinishOption("ترافرتين عاجي", 0xFFEDE6DA),
                FinishOption("رمادي دافئ", 0xFFA49F96)
            ),
            specs = listOf(
                ProductSpec("المقاس", "60 × 120 سم"),
                ProductSpec("السماكة", "10 ملم"),
                ProductSpec("السطح", "مطفي حريري R10 مانع للانزلاق"),
                ProductSpec("الامتصاص", "أقل من 0.1% (بورسلان فل بودي)")
            )
        ),
        ShowroomProduct(
            id = "ceramic_calacatta_gold",
            sku = "NR-CER-308",
            nameAr = "سيراميك كلكتا عروق ذهبية",
            category = ProductCategory.CERAMICS,
            price = 2_650,
            unitAr = "للمتر المربع",
            originAr = "إسبانيا",
            shortTagAr = "لمعة فائقة",
            descriptionAr = "سيراميك وبورسلان إسباني فاخر بلمعة الكريستال وعروق ذهبية ناعمة تمنح الصالات والحمامات اتساعاً وإشراقاً فريداً.",
            imageRes = R.drawable.img_cat_ceramics,
            isFeatured = false,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("أبيض كلكتا ذهبي", 0xFFF3EFE6),
                FinishOption("أبيض ستاتواريو رمادي", 0xFFE5E6E4)
            ),
            specs = listOf(
                ProductSpec("المقاس", "80 × 80 سم / 60 × 120 سم"),
                ProductSpec("السماكة", "9.5 ملم"),
                ProductSpec("الاستخدام", "أرضيات وجدران داخلية"),
                ProductSpec("مقاومة البقع", "الدرجة الخامسة (الأعلى)")
            )
        ),
        ShowroomProduct(
            id = "ceramic_fluted_wall",
            sku = "NR-CER-312",
            nameAr = "سيراميك جداري مضلع ثلاثي الأبعاد",
            category = ProductCategory.CERAMICS,
            price = 2_200,
            unitAr = "للمتر المربع",
            originAr = "إسبانيا",
            shortTagAr = "لمسة معمارية",
            descriptionAr = "بلاط جدران بارز بتصميم أضلاع عمودية ناعمة مثالي لخلفيات المغاسل ومناطق الشاور لإبراز الإضاءة المعمارية.",
            imageRes = R.drawable.img_cat_ceramics,
            isFeatured = false,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("كريمي دافئ", 0xFFE8DFD1),
                FinishOption("أخضر زيتوني هادئ", 0xFF7D8B7E),
                FinishOption("رمادي أردوازي", 0xFF6B7073)
            ),
            specs = listOf(
                ProductSpec("المقاس", "30 × 90 سم"),
                ProductSpec("السماكة", "11 ملم"),
                ProductSpec("النقشة", "أضلاع ثلاثية الأبعاد"),
                ProductSpec("التنظيف", "سهل ومقاوم للبخار")
            )
        ),
        ShowroomProduct(
            id = "ceramic_terrazzo_modern",
            sku = "NR-CER-320",
            nameAr = "بورسلان تيرازو فينيسيا عصري",
            category = ProductCategory.CERAMICS,
            price = 2_850,
            unitAr = "للمتر المربع",
            originAr = "إيطاليا",
            shortTagAr = "مقاوم للاحتكاك",
            descriptionAr = "نقشة التيرازو الكلاسيكية بروح حديثة وحبيبات حجرية متناغمة، مثالي للمطابخ والممرات والحدائق الداخلية.",
            imageRes = R.drawable.img_cat_ceramics,
            isFeatured = false,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("تيرازو أبيض دافئ", 0xFFEAE5DC),
                FinishOption("تيرازو رمادي", 0xFF9EA2A4)
            ),
            specs = listOf(
                ProductSpec("المقاس", "60 × 60 سم"),
                ProductSpec("مقاومة الانزلاق", "R11 مناسب للأماكن الرطبة"),
                ProductSpec("السماكة", "10 ملم"),
                ProductSpec("الاستخدام", "داخلي وخارجي")
            )
        ),

        // ==================== غرانيت (Granite) ====================
        ShowroomProduct(
            id = "granite_nero_marquina",
            sku = "NR-GRN-401",
            nameAr = "غرانيت نيرو جولد الطبيعي",
            category = ProductCategory.GRANITE,
            price = 8_200,
            unitAr = "للمتر المربع",
            originAr = "الهند / البرازيل",
            shortTagAr = "مقاوم للحرارة 100%",
            descriptionAr = "ألواح غرانيت طبيعي فاخر بلون أسود عميق وعروق ذهبية وبيضاء طبيعية، مثالي لأسطح المطابخ، طاولات المغاسل، والدرج الداخلي.",
            imageRes = R.drawable.img_cat_granite,
            isFeatured = true,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("لامع مصقول (Polished)", 0xFF1D1F21),
                FinishOption("ليذر مطفي (Leathered)", 0xFF2F3235)
            ),
            specs = listOf(
                ProductSpec("السماكة", "20 ملم / 30 ملم"),
                ProductSpec("الصلابة", "7 على مقياس موس"),
                ProductSpec("مقاومة الحرارة", "يتحمل الأواني الساخنة مباشرة"),
                ProductSpec("القص", "قص وتفصيل حسب الطلب بالليزر")
            )
        ),
        ShowroomProduct(
            id = "granite_ivory_silk",
            sku = "NR-GRN-406",
            nameAr = "غرانيت أيفوري سيلك الكريمي",
            category = ProductCategory.GRANITE,
            price = 6_800,
            unitAr = "للمتر المربع",
            originAr = "إسبانيا",
            shortTagAr = "لون فاتح هادئ",
            descriptionAr = "غرانيت طبيعي بدرجات العاجي الدافئ مع تموجات حجرية ناعمة، يجمع بين جمال الرخام الفاتح وصلابة الغرانيت الفائقة.",
            imageRes = R.drawable.img_cat_granite,
            isFeatured = false,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("عاجي مصقول", 0xFFE4DAC8),
                FinishOption("حجري هوند مطفي", 0xFFD1C6B4)
            ),
            specs = listOf(
                ProductSpec("السماكة", "20 ملم"),
                ProductSpec("المسامية", "معالج بعازل حماية من البقع"),
                ProductSpec("الاستخدام", "مطابخ، كاونترات، مداخل"),
                ProductSpec("التوفر", "ألواح كاملة ومقاسات مخصصة")
            )
        ),
        ShowroomProduct(
            id = "granite_black_galaxy",
            sku = "NR-GRN-412",
            nameAr = "غرانيت جالاكسي ستار الأصلي",
            category = ProductCategory.GRANITE,
            price = 7_400,
            unitAr = "للمتر المربع",
            originAr = "الهند",
            shortTagAr = "بريق برونزي طبيعي",
            descriptionAr = "غرانيت بلاك جالاكسي الأصلي ذو الخلفية السوداء واللمعان المعدني الذهبي البرونزي، الخيار الأكثر طلباً لأسطح المجالي الفاخرة.",
            imageRes = R.drawable.img_cat_granite,
            isFeatured = false,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("أسود لامع جالاكسي", 0xFF17181A)
            ),
            specs = listOf(
                ProductSpec("السماكة", "20 ملم / 30 ملم"),
                ProductSpec("مقاومة الخدش", "ممتازة جداً"),
                ProductSpec("الحواف", "شطف دائري أو مستقيم 45 درجة"),
                ProductSpec("المعيار", "نخب أول فرز إضافي")
            )
        ),
        ShowroomProduct(
            id = "granite_taj_quartzite",
            sku = "NR-GRN-419",
            nameAr = "كوارتزيت تاج محل الفاخر",
            category = ProductCategory.GRANITE,
            price = 11_200,
            unitAr = "للمتر المربع",
            originAr = "البرازيل",
            shortTagAr = "نخبة الأحجار الطبيعية",
            descriptionAr = "حجر كوارتزيت طبيعي استثنائي بشفافية كريستالية وعروق ذهبية ناعمة على خلفية كريمية، يرفع قيمة أي مساحة معمارية.",
            imageRes = R.drawable.img_cat_granite,
            isFeatured = true,
            isSquareMeterBased = true,
            finishes = listOf(
                FinishOption("شامبين شفاف مصقول", 0xFFDFD3BE),
                FinishOption("ساتان حريري", 0xFFCFC2AC)
            ),
            specs = listOf(
                ProductSpec("السماكة", "20 ملم"),
                ProductSpec("النوع", "كوارتزيت طبيعي 100%"),
                ProductSpec("الاستخدام", "جزر المطابخ، مغاسل، واجهات"),
                ProductSpec("العناية", "تنظيف سهل بدون أحماض")
            )
        )
    )

    fun getById(id: String): ShowroomProduct? = products.firstOrNull { it.id == id }
}
