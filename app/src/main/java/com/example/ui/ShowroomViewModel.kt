package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.SavedSelectionDataStore
import com.example.data.ShowroomCatalogRepository
import com.example.model.ProductCategory
import com.example.model.SavedSelectionItem
import com.example.model.ShowroomProduct
import com.example.model.SortOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ShowroomUiState(
    val allProducts: List<ShowroomProduct> = ShowroomCatalogRepository.products,
    val selectedCategory: ProductCategory? = null,
    val searchQuery: String = "",
    val sortOption: SortOption = SortOption.FEATURED,
    val savedItems: Map<String, SavedSelectionItem> = emptyMap(),
    val isDarkThemeOverride: Boolean? = null
) {
    val featuredProducts: List<ShowroomProduct>
        get() = allProducts.filter { it.isFeatured }

    val filteredProducts: List<ShowroomProduct>
        get() {
            val query = searchQuery.trim()
            val base = allProducts.filter { product ->
                val matchesCategory = selectedCategory == null || product.category == selectedCategory
                val matchesQuery = query.isEmpty() ||
                    product.nameAr.contains(query, ignoreCase = true) ||
                    product.descriptionAr.contains(query, ignoreCase = true) ||
                    product.shortTagAr.contains(query, ignoreCase = true) ||
                    product.category.titleAr.contains(query, ignoreCase = true) ||
                    product.originAr.contains(query, ignoreCase = true) ||
                    product.sku.contains(query, ignoreCase = true) ||
                    product.finishes.any { it.nameAr.contains(query, ignoreCase = true) }
                matchesCategory && matchesQuery
            }
            return when (sortOption) {
                SortOption.FEATURED -> base.sortedByDescending { it.isFeatured }
                SortOption.PRICE_LOW_TO_HIGH -> base.sortedBy { it.price }
                SortOption.PRICE_HIGH_TO_LOW -> base.sortedByDescending { it.price }
            }
        }

    val savedProductDetails: List<Pair<ShowroomProduct, SavedSelectionItem>>
        get() = savedItems.values.mapNotNull { saved ->
            ShowroomCatalogRepository.getById(saved.productId)?.let { it to saved }
        }

    val estimatedSavedTotal: Int
        get() = savedProductDetails.sumOf { (product, saved) -> product.price * saved.quantity }

    val savedCount: Int
        get() = savedItems.size
}

class ShowroomViewModel(application: Application) : AndroidViewModel(application) {

    private val dataStore = SavedSelectionDataStore(application.applicationContext)

    private val selectedCategoryFlow = MutableStateFlow<ProductCategory?>(null)
    private val searchQueryFlow = MutableStateFlow("")
    private val sortOptionFlow = MutableStateFlow(SortOption.FEATURED)
    private val darkThemeOverrideFlow = MutableStateFlow<Boolean?>(null)

    val uiState: StateFlow<ShowroomUiState> = combine(
        selectedCategoryFlow,
        searchQueryFlow,
        sortOptionFlow,
        dataStore.savedItemsFlow,
        darkThemeOverrideFlow
    ) { category, query, sort, saved, darkOverride ->
        ShowroomUiState(
            allProducts = ShowroomCatalogRepository.products,
            selectedCategory = category,
            searchQuery = query,
            sortOption = sort,
            savedItems = saved,
            isDarkThemeOverride = darkOverride
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = ShowroomUiState()
    )

    fun selectCategory(category: ProductCategory?) {
        selectedCategoryFlow.value = category
    }

    fun updateSearchQuery(query: String) {
        searchQueryFlow.value = query
    }

    fun updateSortOption(option: SortOption) {
        sortOptionFlow.value = option
    }

    fun resetFilters() {
        selectedCategoryFlow.value = null
        searchQueryFlow.value = ""
        sortOptionFlow.value = SortOption.FEATURED
    }

    fun toggleDarkTheme(currentlyDark: Boolean) {
        darkThemeOverrideFlow.value = !currentlyDark
    }

    fun toggleSaved(product: ShowroomProduct, finishName: String? = null, initialQty: Int? = null) {
        viewModelScope.launch {
            val existing = uiState.value.savedItems[product.id]
            if (existing != null && initialQty == null) {
                dataStore.remove(product.id)
            } else {
                val chosenFinish = finishName
                    ?: existing?.selectedFinishName
                    ?: product.finishes.firstOrNull()?.nameAr.orEmpty()
                val qty = initialQty
                    ?: existing?.quantity
                    ?: if (product.isSquareMeterBased) 10 else 1
                dataStore.saveOrUpdate(
                    SavedSelectionItem(
                        productId = product.id,
                        selectedFinishName = chosenFinish,
                        quantity = qty.coerceAtLeast(1)
                    )
                )
            }
        }
    }

    fun saveProductWithQuantity(product: ShowroomProduct, finishName: String, quantity: Int) {
        viewModelScope.launch {
            dataStore.saveOrUpdate(
                SavedSelectionItem(
                    productId = product.id,
                    selectedFinishName = finishName,
                    quantity = quantity.coerceAtLeast(1)
                )
            )
        }
    }

    fun updateQuantity(productId: String, delta: Int) {
        viewModelScope.launch {
            val current = uiState.value.savedItems[productId] ?: return@launch
            val nextQty = current.quantity + delta
            if (nextQty <= 0) {
                dataStore.remove(productId)
            } else {
                dataStore.saveOrUpdate(current.copy(quantity = nextQty))
            }
        }
    }

    fun removeSaved(productId: String) {
        viewModelScope.launch {
            dataStore.remove(productId)
        }
    }

    fun clearSaved() {
        viewModelScope.launch {
            dataStore.clearAll()
        }
    }

    fun buildShareSummaryText(): String {
        val state = uiState.value
        if (state.savedProductDetails.isEmpty()) return ""
        val sb = StringBuilder()
        sb.appendLine("قائمة اختياراتي من معرض النور للأدوات الصحية:")
        sb.appendLine("────────────────────")
        state.savedProductDetails.forEachIndexed { index, (product, saved) ->
            val unitLabel = if (product.isSquareMeterBased) "م²" else "قطعة"
            val lineTotal = product.price * saved.quantity
            sb.appendLine("${index + 1}. ${product.nameAr} (${product.sku})")
            sb.appendLine("   • التشطيب: ${saved.selectedFinishName}")
            sb.appendLine("   • الكمية: ${saved.quantity} $unitLabel × ${formatPrice(product.price)} = ${formatPrice(lineTotal)}")
        }
        sb.appendLine("────────────────────")
        sb.appendLine("التكلفة التقديرية الإجمالية: ${formatPrice(state.estimatedSavedTotal)}")
        return sb.toString()
    }
}

fun formatPrice(amount: Int): String {
    val formatted = "%,d".format(java.util.Locale.US, amount)
    return "$formatted ل.س جديدة"
}
