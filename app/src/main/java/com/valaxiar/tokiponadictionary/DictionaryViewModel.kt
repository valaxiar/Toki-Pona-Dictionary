package com.valaxiar.tokiponadictionary

import android.content.Context
import android.content.res.XmlResourceParser
import androidx.annotation.XmlRes
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavController
import com.valaxiar.tokiponadictionary.R
import org.xmlpull.v1.XmlPullParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class DictionaryViewModel: ViewModel() {
    val wordsList = listOf(
        "a",
        "ala",
        "alasa",
        "ale",
        "ali",
        "anpa",
        "ante",
        "anu",
        "awen",
        "e",
        "en",
        "esun",
        "ijo",
        "ike",
        "ilo",
        "insa",
        "jelo",
        "jo",
        "kule",
        "kulupu",
        "la",
        "lape",
        "laso",
        "lawa",
        "len",
        "lete",
        "li",
        "lon",
        "luka",
        "lukin",
        "ma",
        "mama",
        "mani",
        "meli",
        "mi",
        "moku",
        "moli",
        "monsi",
        "mu",
        "mun",
        "musi",
        "mute",
        "nanpa",
        "nasa",
        "nasin",
        "nena",
        "ni",
        "noka",
        "palisa",
        "pana",
        "pi",
        "poka",
        "telo",
        "tenpo",
        "toki",
        "tomo",
        "tu",
        "unpa",
        "usi",
        "utala",
        "walo",
        "wawa",
        "weka",
        "welo",
        "wile"
    )
    private val _uiState = MutableStateFlow(DictionaryUiState())
    val uiState: StateFlow<DictionaryUiState> get() = _uiState
    fun getWordsListSize(): Int {
        return wordsList.size
    }

    fun onCardClick(word: String, def: String, navController: NavController, context: Context) {
        _uiState.value = _uiState.value.copy(currentDefinition = def)
        _uiState.value = _uiState.value.copy(currentWord = word)
        _uiState.value = _uiState.value.copy(
            currentExample = getDictionaryValueFromXml(context, R.xml.examples, word)
        )
        navController.navigate("details")
    }
    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { DictionaryScreen(navController) }
            composable("details") { WordDetailScreen(navController, dictionaryViewModel) }
        }
    }
    fun onBackButtonPress(navController: NavController){
        navController.popBackStack()
    }

    //parse dictionary data from xml file
    fun getDictionaryValueFromXml(
        context: Context, @XmlRes xmlResId: Int, searchKey: String
    ): String {
        val parser: XmlResourceParser = context.resources.getXml(xmlResId)
        var key: String? = null
        var value: String? = null
        try {
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT) {
                when (eventType) {
                    XmlPullParser.START_TAG -> {
                        if (parser.name == "key") {
                            key = parser.nextText()
                        } else if (parser.name == "value") {
                            if (key == searchKey) {
                                value = parser.nextText()
                                break
                            }
                        }
                    }
                }
                eventType = parser.next()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            parser.close()
        }

        //return value
        return value ?: "String is null"
    }
}