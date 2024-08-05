package com.valaxiar.tokiponadictionary

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.XmlResourceParser
import androidx.annotation.XmlRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.xmlpull.v1.XmlPullParser


class DictionaryViewModel: ViewModel() {
    val wordsList = listOf(
        "a", "akesi", "ala", "alasa", "ale", "anpa", "awen", "e", "en", "esun", "ijo", "ike", "ilo", "insa", "jaki", "jan", "jelo", "jo", "kala", "kalama", "kama", "kasi", "ken", "kepeken", "kili", "kiwen", "ko", "kon", "kule", "kulupu", "kute", "la", "lape", "laso", "lawa", "len", "lete", "li", "lili", "linja", "loje", "lon", "luka", "lukin", "ma", "mama", "mani", "meli", "mi", "moku","monsi", "mute", "namako", "nanpa", "noka", "palisa", "pali", "pana", "poki", "pona", "pu", "sama", "selo", "seme", "suli", "telo", "tenpo", "toki", "tomo", "utala", "walo", "wan", "wawa", "weka", "wile"
    )

    @SuppressLint("DiscouragedApi")
    fun getImageResourceId(context: Context, imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    private val _uiState = MutableStateFlow(DictionaryUiState())
    val uiState: StateFlow<DictionaryUiState> get() = _uiState
    fun getWordsListSize(): Int {
        return wordsList.size
    }

    fun onCardClick(word: String, def: String, navController: NavController, context: Context) {
        _uiState.value = _uiState.value.copy(currentDefinition = def)
        _uiState.value = _uiState.value.copy(currentWord = word)
        _uiState.value = _uiState.value.copy(
            currentExample = getDictionaryValueFromXml(context, R.xml.dictionary, word, "example")
        )
        navController.navigate("details")
    }

    fun onExampleClicked(word: String, context: Context, currentExample: String) {
        if (currentExample == getDictionaryValueFromXml(context,R.xml.dictionary,word,"exampletranslation")) {
            //current example is translated
            _uiState.value = _uiState.value.copy(currentExample = getDictionaryValueFromXml(context,R.xml.dictionary,word,"example"))
        } else {
            //current example is not translated
            _uiState.value = _uiState.value.copy(
                currentExample = getDictionaryValueFromXml(
                    context,
                    R.xml.dictionary,
                    word,
                    "exampletranslation"
                )
            )
        }
    }

    @Composable
    fun AppNavigation() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "home") {
            composable("home") { DictionaryScreen(navController) }
            composable("details") { WordDetailScreen(navController, dictionaryViewModel, LocalContext.current) }
        }
    }

    fun onBackButtonPress(navController: NavController) {
        navController.popBackStack()
    }

    // Parse dictionary data from XML file
    fun getDictionaryValueFromXml(
        context: Context,
        @XmlRes xmlResId: Int,
        searchKey: String,
        elementType: String
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
                        } else if (parser.name == elementType) {
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

        // Return value
        return value ?: "String is null"
    }
}