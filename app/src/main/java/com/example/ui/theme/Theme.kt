package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val AlNoorDarkColorScheme = darkColorScheme(
    primary = DarkSlatePrimary,
    onPrimary = DarkSlateOnPrimary,
    primaryContainer = DarkSlateContainer,
    onPrimaryContainer = DarkSlateOnContainer,
    secondary = DarkBrassSecondary,
    onSecondary = DarkBrassOnSecondary,
    secondaryContainer = DarkBrassContainer,
    onSecondaryContainer = DarkBrassOnContainer,
    tertiary = DarkSageTertiary,
    onTertiary = DarkSageOnTertiary,
    tertiaryContainer = DarkSageContainer,
    onTertiaryContainer = DarkSageOnTertiaryContainer,
    background = ObsidianBackground,
    onBackground = IvoryOnDarkSurface,
    surface = ObsidianSurface,
    onSurface = IvoryOnDarkSurface,
    surfaceVariant = ObsidianSurfaceContainer,
    onSurfaceVariant = MistOnDarkSurfaceVariant,
    surfaceContainerLowest = ObsidianBackground,
    surfaceContainerLow = ObsidianSurfaceLow,
    surfaceContainer = ObsidianSurfaceContainer,
    surfaceContainerHigh = ObsidianSurfaceHigh,
    outline = ObsidianOutline,
    outlineVariant = ObsidianOutlineVariant,
    error = DarkErrorRed
)

private val AlNoorLightColorScheme = lightColorScheme(
    primary = SlateTealPrimary,
    onPrimary = SlateTealOnPrimary,
    primaryContainer = SlateTealContainer,
    onPrimaryContainer = SlateTealOnContainer,
    secondary = BrassGoldSecondary,
    onSecondary = BrassGoldOnSecondary,
    secondaryContainer = BrassGoldContainer,
    onSecondaryContainer = BrassGoldOnContainer,
    tertiary = SageStoneTertiary,
    onTertiary = SageStoneOnTertiary,
    tertiaryContainer = SageStoneContainer,
    onTertiaryContainer = SageStoneOnTertiaryContainer,
    background = WarmAlabasterBackground,
    onBackground = InkOnSurface,
    surface = WarmAlabasterSurface,
    onSurface = InkOnSurface,
    surfaceVariant = LimestoneSurface,
    onSurfaceVariant = MutedStoneOnSurfaceVariant,
    surfaceContainerLowest = PorcelainSurfaceLow,
    surfaceContainerLow = PorcelainSurfaceLow,
    surfaceContainer = LimestoneSurface,
    surfaceContainerHigh = LimestoneSurfaceHigh,
    outline = StoneOutline,
    outlineVariant = StoneOutlineVariant,
    error = ErrorRed
)

val AlNoorShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun AlNoorTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> AlNoorDarkColorScheme
        else -> AlNoorLightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AlNoorTypography,
        shapes = AlNoorShapes,
        content = content
    )
}
