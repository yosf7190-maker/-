package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.ShowroomCatalogRepository
import com.example.model.ProductCategory
import com.example.ui.ShowroomUiState
import com.example.ui.formatPrice
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read app name from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("معرض النور", appName)
  }

  @Test
  fun `catalog contains all four core categories`() {
    val categories = ShowroomCatalogRepository.products.map { it.category }.toSet()
    assertTrue(categories.contains(ProductCategory.SINKS))
    assertTrue(categories.contains(ProductCategory.MIXERS))
    assertTrue(categories.contains(ProductCategory.CERAMICS))
    assertTrue(categories.contains(ProductCategory.GRANITE))
  }

  @Test
  fun `price formats in new syrian pound`() {
    assertEquals("22,500 ل.س جديدة", formatPrice(22_500))
  }

  @Test
  fun `search filters products by arabic query`() {
    val state = ShowroomUiState(searchQuery = "غرانيت")
    assertTrue(state.filteredProducts.isNotEmpty())
    assertTrue(state.filteredProducts.all {
      it.category == ProductCategory.GRANITE ||
        it.nameAr.contains("غرانيت") ||
        it.descriptionAr.contains("غرانيت")
    })
  }
}
