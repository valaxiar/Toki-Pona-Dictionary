package com.valaxiar.tokiponadictionary
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun NoSearchResultsView(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "No results found")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { dictionaryViewModel.onBackButtonPress(navController) }) {
            Text(text = "Back")
        }
    }
}


val dictionaryViewModel = DictionaryViewModel()
@Composable
fun DictionaryScreen(navController: NavController) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        item {
            TextField(
                value = dictionaryViewModel.textFieldValue,
                onValueChange = { value: String ->
                    dictionaryViewModel.updateSearchQuery(value)
                },
                label = {Text("Search")},
            )
        }
        items(dictionaryViewModel.getWordsListSize()) { index ->
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                modifier = Modifier
                    .height(170.dp)
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .clickable {
                        dictionaryViewModel.onCardClick(
                            dictionaryViewModel.getWordsList(context, navController)[index],
                            dictionaryViewModel.getDictionaryValueFromXml(
                                context,
                                R.xml.dictionary,
                                dictionaryViewModel.getWordsList(context, navController)[index],
                                "definition"
                            ),
                            navController,
                            context
                        )
                    }
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(
                                dictionaryViewModel.getImageResourceId(
                                    context,
                                    dictionaryViewModel.getWordsList(context, navController)[index]
                                )
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .height(100.dp)
                                .fillMaxHeight()
                                .padding(horizontal = 5.dp)
                        )
                        Column {
                            Text(
                                text = dictionaryViewModel.getWordsList(context, navController)[index],
                                fontSize = 50.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(horizontal = 15.dp),
                                textAlign = TextAlign.Left,
                            )
                            Text(
                                text = dictionaryViewModel.getDictionaryValueFromXml(
                                    context,
                                    R.xml.dictionary,
                                    dictionaryViewModel.getWordsList(context, navController)[index],
                                    "definition"
                                ),
                                fontSize = 30.sp,
                                fontStyle = FontStyle.Italic,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Left,
                                modifier = Modifier.padding(horizontal = 5.dp, vertical = 5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
