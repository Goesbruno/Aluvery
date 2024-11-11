package com.goesbruno.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.goesbruno.aluvery.dao.ProductDao
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.sampleData.sampleCandies
import com.goesbruno.aluvery.sampleData.sampleDrinks
import com.goesbruno.aluvery.sampleData.sampleProducts
import com.goesbruno.aluvery.ui.states.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    private val _uiState: MutableStateFlow<HomeScreenUiState> = MutableStateFlow(HomeScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { searchedText ->
                    _uiState.value = _uiState.value.copy(
                        searchText = searchedText,
                        searchedProducts = searchedProducts(searchedText)
                    )
                }
            )
        }

        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products + sampleProducts,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks
                    ),
                    searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }


    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) ||
                product.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false
    }

    private fun searchedProducts(text: String): List<Product> {
        return if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().value.filter(containsInNameOrDescription(text))
        } else emptyList()
    }


}