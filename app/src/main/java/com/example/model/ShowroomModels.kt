package com.example.model

import androidx.annotation.DrawableRes
import com.example.R

enum class ProductCategory(
    val titleAr: String,
    val subtitleAr: String,
    @DrawableRes val imageRes: Int
) {
    SINKS(
        titleAr = "مغاسل",
        subtitleAr = "خزف فاخر ورخام",
        imageRes = R.drawable.img_cat_sinks
    ),
    MIXERS(
        titleAr = "خلاطات",
        subtitleAr = "تصاميم أوروبية وعصرية",
        imageRes = R.drawable.img_cat_mixers
    ),
    CERAMICS(
        titleAr = "سيراميك",
        subtitleAr = "بورسلان وبلاط جدران",
        imageRes = R.drawable.img_cat_ceramics
    ),
    GRANITE(
        titleAr = "غرانيت",
        subtitleAr = "ألواح طبيعية ومجالي",
        imageRes = R.drawable.img_cat_granite
    )
}

data class FinishOption(
    val nameAr: String,
    val colorHex: Long
)

data class ProductSpec(
    val labelAr: String,
    val valueAr: String
)

data class ShowroomProduct(
    val id: String,
    val sku: String,
    val nameAr: String,
    val category: ProductCategory,
    val price: Int,
    val unitAr: String,
    val originAr: String,
    val shortTagAr: String,
    val descriptionAr: String,
    @DrawableRes val imageRes: Int,
    val isFeatured: Boolean,
    val finishes: List<FinishOption>,
    val specs: List<ProductSpec>,
    val isSquareMeterBased: Boolean = false
)

data class SavedSelectionItem(
    val productId: String,
    val selectedFinishName: String,
    val quantity: Int
)

enum class SortOption(val labelAr: String) {
    FEATURED("الأكثر تميزاً"),
    PRICE_LOW_TO_HIGH("الأقل سعراً"),
    PRICE_HIGH_TO_LOW("الأعلى سعراً")
}
