package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.R
import com.example.model.ProductCategory
import com.example.model.ShowroomProduct
import com.example.ui.ShowroomUiState
import com.example.ui.components.AlNoorBrandHeader
import com.example.ui.components.ProductShowcaseCard
import com.example.ui.components.SectionHeaderRow
import com.example.ui.components.ShowroomSearchBar

@Composable
fun HomeScreen(
    uiState: ShowroomUiState,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onNavigateToCatalog: (ProductCategory?) -> Unit,
    onProductClick: (ShowroomProduct) -> Unit,
    onToggleSaved: (ShowroomProduct) -> Unit,
    modifier: Modifier = Modifier
) {
    val activeQuery = uiState.searchQuery.trim()
    val searchResults = if (activeQuery.isNotEmpty()) {
        uiState.allProducts.filter { product ->
            product.nameAr.contains(activeQuery, ignoreCase = true) ||
                product.descriptionAr.contains(activeQuery, ignoreCase = true) ||
                product.shortTagAr.contains(activeQuery, ignoreCase = true) ||
                product.category.titleAr.contains(activeQuery, ignoreCase = true) ||
                product.originAr.contains(activeQuery, ignoreCase = true) ||
                product.sku.contains(activeQuery, ignoreCase = true) ||
                product.finishes.any { it.nameAr.contains(activeQuery, ignoreCase = true) }
        }
    } else {
        emptyList()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                AlNoorBrandHeader(
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = onToggleTheme
                )
                ShowroomSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = onSearchQueryChange,
                    inputTestTag = "home_search_input",
                    clearTestTag = "home_search_clear_button",
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }

        if (activeQuery.isNotEmpty()) {
            item {
                SectionHeaderRow(
                    title = "نتائج البحث (${searchResults.size})",
                    actionLabel = "عرض بالكتالوج",
                    onActionClick = { onNavigateToCatalog(null) }
                )
            }

            if (searchResults.isEmpty()) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 24.dp, vertical = 32.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.SearchOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.empty_search_title),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            text = "جرب البحث عن: مغسلة، خلاط، بورسلان، غرانيت، ذهبي",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        OutlinedButton(
                            onClick = { onSearchQueryChange("") },
                            modifier = Modifier.testTag("home_clear_search_empty")
                        ) {
                            Text(text = stringResource(id = R.string.search_clear_desc))
                        }
                    }
                }
            } else {
                items(
                    items = searchResults.chunked(2),
                    key = { row -> row.joinToString("-") { it.id } }
                ) { rowProducts ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        rowProducts.forEach { product ->
                            ProductShowcaseCard(
                                product = product,
                                isSaved = uiState.savedItems.containsKey(product.id),
                                onProductClick = { onProductClick(product) },
                                onToggleSaved = { onToggleSaved(product) },
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (rowProducts.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        } else {
            // Architectural Hero Showroom Card
            item {
                ShowroomHeroBanner(
                    onExploreClick = { onNavigateToCatalog(null) },
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            // 4 Main Categories Grid (مغاسل، خلاطات، سيراميك، غرانيت)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    SectionHeaderRow(
                        title = stringResource(id = R.string.section_categories),
                        actionLabel = stringResource(id = R.string.section_view_all),
                        onActionClick = { onNavigateToCatalog(null) }
                    )

                    val categories = ProductCategory.entries
                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        categories.chunked(2).forEach { rowCategories ->
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                rowCategories.forEach { category ->
                                    val itemCount = uiState.allProducts.count { it.category == category }
                                    CategoryTileCard(
                                        category = category,
                                        itemCount = itemCount,
                                        onClick = { onNavigateToCatalog(category) },
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Featured Collection Horizontal Carousel
            item {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    SectionHeaderRow(
                        title = stringResource(id = R.string.section_featured),
                        actionLabel = stringResource(id = R.string.section_view_all),
                        onActionClick = { onNavigateToCatalog(null) }
                    )

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(
                            items = uiState.featuredProducts,
                            key = { it.id }
                        ) { product ->
                            ProductShowcaseCard(
                                product = product,
                                isSaved = uiState.savedItems.containsKey(product.id),
                                onProductClick = { onProductClick(product) },
                                onToggleSaved = { onToggleSaved(product) },
                                modifier = Modifier.width(236.dp)
                            )
                        }
                    }
                }
            }

            // Showroom Trust Pillars
            item {
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "لماذا معرض النور؟",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TrustHighlightCard(
                            icon = Icons.Filled.Verified,
                            title = "ضمان حقيقي",
                            subtitle = "حتى 10 سنوات",
                            modifier = Modifier.weight(1f)
                        )
                        TrustHighlightCard(
                            icon = Icons.Filled.WorkspacePremium,
                            title = "نخب أول",
                            subtitle = "إيطالي وإسباني",
                            modifier = Modifier.weight(1f)
                        )
                        TrustHighlightCard(
                            icon = Icons.Filled.Architecture,
                            title = "استشارة مجانية",
                            subtitle = "حساب المساحات",
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ShowroomHeroBanner(
    onExploreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.large,
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(236.dp)
            .clip(MaterialTheme.shapes.large)
            .clickable(onClick = onExploreClick)
            .testTag("hero_banner_card")
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.img_hero_showroom),
                contentDescription = stringResource(id = R.string.hero_title),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xD90E222B),
                                Color(0x88163844),
                                Color(0x330E222B)
                            )
                        )
                    )
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.92f)
                ) {
                    Text(
                        text = stringResource(id = R.string.hero_badge),
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = stringResource(id = R.string.hero_title),
                        style = MaterialTheme.typography.headlineMedium,
                        color = Color.White
                    )
                    Text(
                        text = stringResource(id = R.string.hero_description),
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.90f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Button(
                        onClick = onExploreClick,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.surface,
                            contentColor = MaterialTheme.colorScheme.primary
                        ),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                        modifier = Modifier.testTag("hero_cta_button")
                    ) {
                        Text(
                            text = stringResource(id = R.string.hero_cta),
                            style = MaterialTheme.typography.labelLarge
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CategoryTileCard(
    category: ProductCategory,
    itemCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
            .aspectRatio(1.22f)
            .clip(MaterialTheme.shapes.medium)
            .clickable(onClick = onClick)
            .testTag("category_card_${category.name.lowercase()}")
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = category.imageRes),
                contentDescription = category.titleAr,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color(0x1A000000),
                                Color(0xCC0D1E25)
                            )
                        )
                    )
            )

            Surface(
                shape = RoundedCornerShape(50),
                color = Color.Black.copy(alpha = 0.45f),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                Text(
                    text = "$itemCount موديلات",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = category.titleAr,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
                Text(
                    text = category.subtitleAr,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.85f),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
private fun TrustHighlightCard(
    icon: ImageVector,
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceContainer,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(vertical = 14.dp, horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
