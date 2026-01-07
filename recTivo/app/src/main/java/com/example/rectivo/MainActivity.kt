package com.example.rectivo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.rectivo.ui.RectivoApp
import com.example.rectivo.ui.theme.RecTivoTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecTivoTheme {
                RectivoApp()


                }
            }
        }
    }


