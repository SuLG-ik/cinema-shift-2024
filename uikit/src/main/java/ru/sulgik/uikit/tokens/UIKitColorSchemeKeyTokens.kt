package ru.sulgik.uikit.tokens

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color

enum class UIKitColorSchemeKeyTokens {
    Background,
    Error,
    ErrorContainer,
    InverseOnSurface,
    InversePrimary,
    InverseSurface,
    OnBackground,
    OnError,
    OnErrorContainer,
    OnPrimary,
    OnPrimaryContainer,
    OnSecondary,
    OnSecondaryContainer,
    OnSurface,
    OnSurfaceVariant,
    OnTertiary,
    OnTertiaryContainer,
    Outline,
    OutlineVariant,
    Primary,
    PrimaryContainer,
    Scrim,
    Secondary,
    SecondaryContainer,
    Surface,
    SurfaceTint,
    SurfaceVariant,
    Tertiary,
    TertiaryContainer,
}

private fun ColorScheme.fromToken(value: UIKitColorSchemeKeyTokens): Color {
    return when (value) {
        UIKitColorSchemeKeyTokens.Background -> background
        UIKitColorSchemeKeyTokens.Error -> error
        UIKitColorSchemeKeyTokens.ErrorContainer -> errorContainer
        UIKitColorSchemeKeyTokens.InverseOnSurface -> inverseOnSurface
        UIKitColorSchemeKeyTokens.InversePrimary -> inversePrimary
        UIKitColorSchemeKeyTokens.InverseSurface -> inverseSurface
        UIKitColorSchemeKeyTokens.OnBackground -> onBackground
        UIKitColorSchemeKeyTokens.OnError -> onError
        UIKitColorSchemeKeyTokens.OnErrorContainer -> onErrorContainer
        UIKitColorSchemeKeyTokens.OnPrimary -> onPrimary
        UIKitColorSchemeKeyTokens.OnPrimaryContainer -> onPrimaryContainer
        UIKitColorSchemeKeyTokens.OnSecondary -> onSecondary
        UIKitColorSchemeKeyTokens.OnSecondaryContainer -> onSecondaryContainer
        UIKitColorSchemeKeyTokens.OnSurface -> onSurface
        UIKitColorSchemeKeyTokens.OnSurfaceVariant -> onSurfaceVariant
        UIKitColorSchemeKeyTokens.SurfaceTint -> surfaceTint
        UIKitColorSchemeKeyTokens.OnTertiary -> onTertiary
        UIKitColorSchemeKeyTokens.OnTertiaryContainer -> onTertiaryContainer
        UIKitColorSchemeKeyTokens.Outline -> outline
        UIKitColorSchemeKeyTokens.OutlineVariant -> outlineVariant
        UIKitColorSchemeKeyTokens.Primary -> primary
        UIKitColorSchemeKeyTokens.PrimaryContainer -> primaryContainer
        UIKitColorSchemeKeyTokens.Scrim -> scrim
        UIKitColorSchemeKeyTokens.Secondary -> secondary
        UIKitColorSchemeKeyTokens.SecondaryContainer -> secondaryContainer
        UIKitColorSchemeKeyTokens.Surface -> surface
        UIKitColorSchemeKeyTokens.SurfaceVariant -> surfaceVariant
        UIKitColorSchemeKeyTokens.Tertiary -> tertiary
        UIKitColorSchemeKeyTokens.TertiaryContainer -> tertiaryContainer
    }
}


@ReadOnlyComposable
@Composable
internal fun UIKitColorSchemeKeyTokens.toColor(): Color {
    return MaterialTheme.colorScheme.fromToken(this)
}
