package com.valaxiar.tokiponadictionary

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WordDetailScreen(navController: NavController, viewModel: DictionaryViewModel){
    val uiState by viewModel.uiState.collectAsState()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //word
        Text(
            text = uiState.currentWord,
            fontSize = 80.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .padding(30.dp)
                .padding(top = 30.dp)
        )
        //definition
        Text(
            text = uiState.currentDefinition,
            fontSize = 30.sp,
            modifier = Modifier
            .padding(30.dp)
        )
        //example sentence
        Text(
            text = uiState.currentExample,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(30.dp)
        )
            Button(
                onClick = { dictionaryViewModel.onBackButtonPress(navController) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(top = 25.dp)
            ) {
                Text("Back")
            }
    }
}