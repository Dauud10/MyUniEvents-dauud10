package com.example.myunievents.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Light color scheme
private val LightColors = lightColorScheme(
    primary = HeaderGreen,
    onPrimary = TextWhite,
    secondary = MainGreen,
    onSecondary = TextBlack,
    background = MainGreen,
    onBackground = TextBlack,
    surface = MainGreen,
    onSurface = TextBlack
)

// Dark color scheme
private val DarkColors = darkColorScheme(
    primary = HeaderGreen,
    onPrimary = TextWhite,
    secondary = MainGreen,
    onSecondary = TextWhite,
    background = Color.Black,
    onBackground = TextWhite,
    surface = Color.DarkGray,
    onSurface = TextWhite
)

@Composable
fun MyUniEventsTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as? Activity)?.window
            window?.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window!!, view)
                .isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
