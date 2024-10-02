package com.valaxiar.tokiponadictionary.presentation

data class DictionaryUiState(
    val currentWord: String = "",
    val currentDefinition: String = "",
    var currentExample: String = "",
)