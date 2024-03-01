package com.example.htnotpadtestapp.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Blue80,
    onPrimary  = Color.White,
    primaryContainer = Blue30,
    onPrimaryContainer = Blue90,
    inversePrimary = Blue40,
    secondary = DarkBlue80,
    onSecondary = DarkBlue20,
    secondaryContainer = DarkBlue30,
    onSecondaryContainer = DarkBlue90,
    tertiary = Yellow80,
    onTertiary = Yellow20,
    tertiaryContainer = Yellow30,
    onTertiaryContainer = Yellow90,
    error = Red80,
    onError = Red20,
    errorContainer = Red30,
    onErrorContainer = Red90,
    background = Grey10,
    onBackground = Color.White,
    surface = Grey10,
    onSurface = Color.White,
    inverseSurface = Grey90,
    inverseOnSurface = Grey20,
    surfaceVariant = BlueGrey30,
    onSurfaceVariant = BlueGrey80,
    outline = BlueGrey60
)

private val LightColorScheme = lightColorScheme(
    primary = DarkBlue40,
    onPrimary = Color.Black,
    primaryContainer = Blue90,
    onPrimaryContainer = Blue10,
    inversePrimary = Blue80,
    secondary = DarkBlue30,
    onSecondary = Color.White,
    secondaryContainer = DarkBlue30,
    onSecondaryContainer = DarkBlue10,
    tertiary = Yellow40,
    onTertiary = Color.White,
    tertiaryContainer = Yellow90,
    onTertiaryContainer = Yellow10,
    error = Red40,
    onError = Color.White,
    errorContainer = Red90,
    onErrorContainer = Red10,
    background = Grey95,
    onBackground = Color.Black,
    surface = Grey99,
    onSurface = Color.Black,
    inverseSurface = Grey20,
    inverseOnSurface = Grey95,
    surfaceVariant = Color.White,
    onSurfaceVariant = DarkBlue,
    outline = BlueGrey50
)


val ColorScheme.textColor2: Color @Composable
get() = if (isSystemInDarkTheme()) Color.White else Black20

val ColorScheme.textColor3: Color @Composable
get() = if (isSystemInDarkTheme()) Color.White else Grey30

val ColorScheme.textColor4: Color @Composable
get() = if (isSystemInDarkTheme()) Color.White else Grey50

val ColorScheme.textColor5: Color @Composable
get() = if (isSystemInDarkTheme()) Color.White else Grey24


@Composable
fun HTNotpadTestAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = Grey10
        )
    }else{
        systemUiController.setSystemBarsColor(
            color = Grey95,
            darkIcons = true
        )
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}