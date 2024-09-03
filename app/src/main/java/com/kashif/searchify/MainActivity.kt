package com.kashif.searchify

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.ui.Modifier
import com.example.shared.di.KoinModule
import com.kashif.designsystem.theme.SearchifyTheme
import com.kashif.searchify.ui.NavHost
import dagger.hilt.android.AndroidEntryPoint
import org.koin.compose.KoinApplication
import org.koin.dsl.koinApplication
import org.koin.ksp.generated.module

fun getKoinConfiguration() = koinApplication{
    modules(
        KoinModule().module
    )
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            SearchifyTheme {
                KoinApplication(application = ::getKoinConfiguration) {
                    NavHost(modifier = Modifier.windowInsetsPadding(WindowInsets.systemBars))
                }

            }
        }
    }
}

