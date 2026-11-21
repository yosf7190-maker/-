package com.example.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.GridView
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Storefront
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.R
import com.example.data.ShowroomCatalogRepository
import com.example.ui.screens.CatalogScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductDetailScreen
import com.example.ui.screens.SavedQuotesScreen
import com.example.ui.screens.ShowroomInfoScreen
import com.example.ui.theme.AlNoorTheme

object AlNoorRoutes {
    const val HOME = "home"
    const val CATALOG = "catalog"
    const val SAVED = "saved"
    const val SHOWROOM = "showroom"
    const val PRODUCT_DETAIL = "product/{productId}"

    fun productDetail(productId: String): String = "product/$productId"
}

private enum class TopDestination(
    val route: String,
    val labelRes: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    HOME(
        route = AlNoorRoutes.HOME,
        labelRes = R.string.nav_home,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    CATALOG(
        route = AlNoorRoutes.CATALOG,
        labelRes = R.string.nav_catalog,
        selectedIcon = Icons.Filled.GridView,
        unselectedIcon = Icons.Outlined.GridView
    ),
    SAVED(
        route = AlNoorRoutes.SAVED,
        labelRes = R.string.nav_saved,
        selectedIcon = Icons.Filled.Bookmark,
        unselectedIcon = Icons.Outlined.BookmarkBorder
    ),
    SHOWROOM(
        route = AlNoorRoutes.SHOWROOM,
        labelRes = R.string.nav_showroom,
        selectedIcon = Icons.Filled.Storefront,
        unselectedIcon = Icons.Outlined.Storefront
    )
}

@Composable
fun AlNoorApp(
    viewModel: ShowroomViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val systemDark = isSystemInDarkTheme()
    val isDarkTheme = uiState.isDarkThemeOverride ?: systemDark

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        AlNoorTheme(darkTheme = isDarkTheme) {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            val isTopLevelRoute = TopDestination.entries.any { dest ->
                currentDestination?.hierarchy?.any { it.route == dest.route } == true
            }

            fun navigateToTopLevel(route: String) {
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                val useNavRail = maxWidth >= 600.dp

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentWindowInsets = if (isTopLevelRoute) {
                        WindowInsets.safeDrawing
                    } else {
                        WindowInsets(0, 0, 0, 0)
                    },
                    bottomBar = {
                        if (isTopLevelRoute && !useNavRail) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                tonalElevation = 3.dp
                            ) {
                                TopDestination.entries.forEach { destination ->
                                    val selected = currentDestination?.hierarchy?.any {
                                        it.route == destination.route
                                    } == true
                                    NavigationBarItem(
                                        selected = selected,
                                        onClick = { navigateToTopLevel(destination.route) },
                                        icon = {
                                            DestinationIconWithBadge(
                                                destination = destination,
                                                selected = selected,
                                                savedCount = uiState.savedCount
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(id = destination.labelRes),
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                        ),
                                        modifier = Modifier.testTag("nav_${destination.route}")
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (isTopLevelRoute && useNavRail) {
                            NavigationRail(
                                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
                                modifier = Modifier.fillMaxHeight()
                            ) {
                                TopDestination.entries.forEach { destination ->
                                    val selected = currentDestination?.hierarchy?.any {
                                        it.route == destination.route
                                    } == true
                                    NavigationRailItem(
                                        selected = selected,
                                        onClick = { navigateToTopLevel(destination.route) },
                                        icon = {
                                            DestinationIconWithBadge(
                                                destination = destination,
                                                selected = selected,
                                                savedCount = uiState.savedCount
                                            )
                                        },
                                        label = {
                                            Text(
                                                text = stringResource(id = destination.labelRes),
                                                style = MaterialTheme.typography.labelSmall
                                            )
                                        },
                                        colors = NavigationRailItemDefaults.colors(
                                            selectedIconColor = MaterialTheme.colorScheme.primary,
                                            selectedTextColor = MaterialTheme.colorScheme.primary,
                                            indicatorColor = MaterialTheme.colorScheme.primaryContainer
                                        ),
                                        modifier = Modifier.testTag("rail_${destination.route}")
                                    )
                                }
                            }
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight(),
                            contentAlignment = Alignment.TopCenter
                        ) {
                            NavHost(
                                navController = navController,
                                startDestination = AlNoorRoutes.HOME,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .widthIn(max = 960.dp)
                            ) {
                                composable(AlNoorRoutes.HOME) {
                                    HomeScreen(
                                        uiState = uiState,
                                        isDarkTheme = isDarkTheme,
                                        onToggleTheme = { viewModel.toggleDarkTheme(isDarkTheme) },
                                        onSearchQueryChange = viewModel::updateSearchQuery,
                                        onNavigateToCatalog = { category ->
                                            viewModel.selectCategory(category)
                                            navigateToTopLevel(AlNoorRoutes.CATALOG)
                                        },
                                        onProductClick = { product ->
                                            navController.navigate(AlNoorRoutes.productDetail(product.id))
                                        },
                                        onToggleSaved = { product ->
                                            viewModel.toggleSaved(product)
                                        }
                                    )
                                }

                                composable(AlNoorRoutes.CATALOG) {
                                    CatalogScreen(
                                        uiState = uiState,
                                        onSelectCategory = viewModel::selectCategory,
                                        onSearchQueryChange = viewModel::updateSearchQuery,
                                        onSortOptionChange = viewModel::updateSortOption,
                                        onResetFilters = viewModel::resetFilters,
                                        onProductClick = { product ->
                                            navController.navigate(AlNoorRoutes.productDetail(product.id))
                                        },
                                        onToggleSaved = { product ->
                                            viewModel.toggleSaved(product)
                                        }
                                    )
                                }

                                composable(AlNoorRoutes.SAVED) {
                                    SavedQuotesScreen(
                                        uiState = uiState,
                                        onProductClick = { product ->
                                            navController.navigate(AlNoorRoutes.productDetail(product.id))
                                        },
                                        onUpdateQuantity = viewModel::updateQuantity,
                                        onRemoveItem = viewModel::removeSaved,
                                        onClearAll = viewModel::clearSaved,
                                        onExploreCatalog = {
                                            navigateToTopLevel(AlNoorRoutes.CATALOG)
                                        },
                                        buildShareText = viewModel::buildShareSummaryText
                                    )
                                }

                                composable(AlNoorRoutes.SHOWROOM) {
                                    ShowroomInfoScreen()
                                }

                                composable(
                                    route = AlNoorRoutes.PRODUCT_DETAIL,
                                    arguments = listOf(
                                        navArgument("productId") { type = NavType.StringType }
                                    )
                                ) { backStackEntry ->
                                    val productId = backStackEntry.arguments?.getString("productId")
                                    val product = productId?.let { ShowroomCatalogRepository.getById(it) }
                                    if (product != null) {
                                        ProductDetailScreen(
                                            product = product,
                                            savedItem = uiState.savedItems[product.id],
                                            onBack = { navController.popBackStack() },
                                            onSaveSelection = { finishName, qty ->
                                                viewModel.saveProductWithQuantity(product, finishName, qty)
                                            },
                                            onRemoveSelection = {
                                                viewModel.removeSaved(product.id)
                                            }
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DestinationIconWithBadge(
    destination: TopDestination,
    selected: Boolean,
    savedCount: Int
) {
    val iconVector = if (selected) destination.selectedIcon else destination.unselectedIcon
    if (destination == TopDestination.SAVED && savedCount > 0) {
        BadgedBox(
            badge = {
                Badge(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary
                ) {
                    Text(text = savedCount.toString())
                }
            }
        ) {
            Icon(
                imageVector = iconVector,
                contentDescription = stringResource(id = destination.labelRes)
            )
        }
    } else {
        Icon(
            imageVector = iconVector,
            contentDescription = stringResource(id = destination.labelRes)
        )
    }
}
