package com.valaxiar.tokiponadictionary
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.valaxiar.tokiponadictionary.R


val dictionaryViewModel = DictionaryViewModel()
@Composable
fun DictionaryScreen(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        item {
            Text(
                text = "Toki Pona Dictionary",
                fontSize = 35.sp,
                modifier = Modifier.padding(30.dp)
            )
        }
        items(dictionaryViewModel.getWordsListSize()) { index ->
            Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 10.dp)
                    .clickable {
                        dictionaryViewModel.onCardClick(
                            dictionaryViewModel.wordsList[index],
                            dictionaryViewModel.getDictionaryValueFromXml(
                                context,
                                R.xml.dictionary,
                                dictionaryViewModel.wordsList[index]
                            ),
                            navController,
                            context
                        )
                    }
            ) {

                Text(
                    text = dictionaryViewModel.wordsList[index],
                    fontSize = 60.sp,
                    modifier = Modifier
                        .padding(vertical = 16.dp, horizontal = 15.dp),
                    textAlign = TextAlign.Left,
                ) 
                Text(
                    text =  dictionaryViewModel.getDictionaryValueFromXml(context,R.xml.dictionary,dictionaryViewModel.wordsList[index]),
                    fontSize = 30.sp,
                    fontStyle = FontStyle.Italic,
                    textAlign = TextAlign.Left,
                    modifier = Modifier.padding(horizontal = 15.dp)
                )
            }
        }
    }
}