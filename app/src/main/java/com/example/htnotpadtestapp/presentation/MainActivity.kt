package com.example.htnotpadtestapp.presentation

import android.Manifest.permission.POST_NOTIFICATIONS
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.htnotpadtestapp.presentation.addnote.navigateToAddNote
import com.example.htnotpadtestapp.presentation.components.HTBottomBar
import com.example.htnotpadtestapp.presentation.navigation.HTNavHost
import com.example.htnotpadtestapp.presentation.navigation.HTAppState
import com.example.htnotpadtestapp.presentation.navigation.rememberHTAppState
import com.example.htnotpadtestapp.presentation.theme.HTNotpadTestAppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        setContent {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                CheckNotificationPermission()
            }
            HTNotpadTestAppTheme {
                HTApp()
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun HTApp() {
        val appState: HTAppState = rememberHTAppState()

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Bottom,
            ) {
                    HTNavHost(
                        modifier = Modifier
                            .weight(1f),
                        appState.navController
                    )
                if (appState.isTopLevelNavigation) {
                    HTBottomBar(
                        onNavigateToDestination = appState::navigateToTopLevelDestination,
                        currentDestination = appState.currentDestination,
                        onAddClick = appState.navController::navigateToAddNote
                    )
                }

            }
        }
    }

    @OptIn(ExperimentalPermissionsApi::class)
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    private fun CheckNotificationPermission() {
        val permissionState = rememberPermissionState(
            permission = POST_NOTIFICATIONS
        )
        if (permissionState.status is PermissionStatus.Denied) {
            LaunchedEffect(key1 = Unit, block = {
                permissionState.launchPermissionRequest()
            })
            //if (permissionState.status.shouldShowRationale) {
            //TODO: we can ask next time
        }
    }
}

