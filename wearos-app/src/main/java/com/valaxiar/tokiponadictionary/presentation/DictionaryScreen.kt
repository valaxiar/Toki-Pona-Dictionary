package com.valaxiar.tokiponadictionary.presentation
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import com.valaxiar.tokiponadictionary.R


val dictionaryViewModel = DictionaryViewModel()
@Composable
fun DictionaryScreen(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        items(dictionaryViewModel.getWordsListSize()) { index ->
            Chip(
                onClick = {dictionaryViewModel.onCardClick( dictionaryViewModel.wordsList[index], dictionaryViewModel.getDictionaryValueFromXml( context, R.xml.dictionary, dictionaryViewModel.wordsList[index], "definition" ), navController, context )},
                enabled = true,
                // Primary label has maximum 3 lines, Secondary label has maximum 2 lines.
                label = { Text(text = dictionaryViewModel.wordsList[index], maxLines = 3, overflow = TextOverflow.Ellipsis) },
                secondaryLabel = {
                    Text(text = dictionaryViewModel.getDictionaryValueFromXml(context,
                        R.xml.dictionary,
                        dictionaryViewModel.wordsList[index],"definition"), maxLines = 2, overflow = TextOverflow.Ellipsis)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = dictionaryViewModel.getImageResourceId(context,
                            dictionaryViewModel.wordsList[index])),
                        contentDescription = "airplane",
                        modifier = Modifier.size(ChipDefaults.IconSize)
                            .wrapContentSize(align = Alignment.Center),
                    )
                }
            )
        }
    }
}
