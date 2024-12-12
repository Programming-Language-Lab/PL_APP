package com.soopeach.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.soopeach.presentation.screens.AppScreen
import com.soopeach.presentation.ui.theme.Pl_labTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pl_labTheme {
                AppScreen()
            }
        }
    }
}