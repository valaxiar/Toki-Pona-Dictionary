package com.valaxiar.tokiponadictionary.presentation

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import com.valaxiar.tokiponadictionary.R

@Composable
fun WordDetailScreen(viewModel: DictionaryViewModel, context: Context) {
    val uiState by viewModel.uiState.collectAsState()
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
    ) {
       item {
           Icon(
               painter = painterResource(id = dictionaryViewModel.getImageResourceId(context, uiState.currentWord)),
               contentDescription = null,
               modifier = Modifier
                   .size(100.dp)
                   .padding(top = 10.dp)
           )
       }

        item {
            Text(
                text = uiState.currentWord,
                fontSize = 45.sp,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.padding(vertical = 10.dp)
            )
        }
        item {
            Chip(
                onClick = {},
                modifier = Modifier.padding(horizontal = 8.dp),
                colors = ChipDefaults.chipColors(),
                label = {
                    Text(
                        text = uiState.currentDefinition,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            )
        }
        item {
            Chip(
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .padding(vertical = 5.dp, horizontal = 8.dp),
                onClick = {dictionaryViewModel.onExampleClicked(
                    uiState.currentWord,
                    context,
                    uiState.currentExample
                ) },
                colors = ChipDefaults.chipColors(),
                label = {
                    Text(
                        text = uiState.currentExample,
                    )
                },
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.refresh),
                        contentDescription = "airplane",
                        modifier = Modifier.size(ChipDefaults.IconSize)
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            )
        }
    }
}