package com.valaxiar.tokiponadictionary.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.valaxiar.tokiponadictionary.presentation.theme.TokiPonaDictionaryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TokiPonaDictionaryTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    val dictionaryModel = DictionaryViewModel()
                    dictionaryModel.AppNavigation()
                }
            }
        }
    }
}
