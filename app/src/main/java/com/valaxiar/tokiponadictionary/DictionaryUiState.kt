package com.valaxiar.tokiponadictionary

data class DictionaryUiState(
    val currentWord: String = "",
    val currentDefinition: String = "",
    var currentExample: String = ""
)