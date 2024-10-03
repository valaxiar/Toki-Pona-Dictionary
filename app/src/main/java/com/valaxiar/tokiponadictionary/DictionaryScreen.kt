package com.valaxiar.tokiponadictionary
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


val dictionaryViewModel = DictionaryViewModel()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(navController: NavController) {
    val context = LocalContext.current
    val wordsList = dictionaryViewModel.getWordsList(context)

    LazyColumn(modifier = Modifier.padding(top = 70.dp)) {
        item {
            TextField(
                value = dictionaryViewModel.textFieldValue,
                shape = RoundedCornerShape(25.dp),
                onValueChange = { value: String -> dictionaryViewModel.updateSearchQuery(value) },
                label = { Text("Search") },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }

        if (dictionaryViewModel.resultsFound) {
            items(wordsList.size) { index ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                    modifier = Modifier
                        .height(170.dp)
                        .fillMaxSize()
                        .padding(vertical = 10.dp, horizontal = 8.dp)
                        .clickable {
                            dictionaryViewModel.onCardClick(
                                wordsList[index],
                                dictionaryViewModel.getDictionaryValueFromXml(
                                    context,
                                    R.xml.dictionary,
                                    wordsList[index],
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
                                        wordsList[index]
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
                                    text = wordsList[index],
                                    fontSize = 50.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 15.dp),
                                    textAlign = TextAlign.Left
                                )
                                Text(
                                    text = dictionaryViewModel.getDictionaryValueFromXml(
                                        context,
                                        R.xml.dictionary,
                                        wordsList[index],
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
        } else {
            item {
                Text(text = "No results found", modifier = Modifier.padding(16.dp), textAlign = TextAlign.Center)
            }
        }
    }
}
