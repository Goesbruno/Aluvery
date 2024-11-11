package com.goesbruno.aluvery.ui.states

import com.goesbruno.aluvery.model.Product

data class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    var searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }

}