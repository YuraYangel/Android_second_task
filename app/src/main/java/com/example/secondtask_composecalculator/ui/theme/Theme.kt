package com.example.secondtask_composecalculator.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = Purple500,
    secondary = Teal200

)

@Composable
fun DynamicTheme(
    isDynamicColor: Boolean = true,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val dynamicColor = isDynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    val colors = if (darkTheme) {
        if (dynamicColor) {
            dynamicDarkColorScheme(LocalContext.current)
        } else {
            DarkColorPalette
        }
    } else {
        if (dynamicColor) {
            dynamicLightColorScheme(LocalContext.current)
        }
        LightColorPalette
    }
    SideEffect{
        systemUiController.setSystemBarsColor(
            color = colors.background,
            darkIcons = false
        )
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}