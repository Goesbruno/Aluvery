package com.goesbruno.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.sampleData.sampleCandies
import com.goesbruno.aluvery.sampleData.sampleDrinks
import com.goesbruno.aluvery.sampleData.sampleProducts
import com.goesbruno.aluvery.sampleData.sampleSections
import com.goesbruno.aluvery.ui.components.CardProductItem
import com.goesbruno.aluvery.ui.components.ProductsSection
import com.goesbruno.aluvery.ui.components.SeachTextField


class HomeScreenUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    var searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {

    fun isShowSections(): Boolean {
        return searchText.isBlank()
    }

}

@Composable
fun HomeScreen(
    products: List<Product>
) {
    val sections = mapOf(
        "Todos produtos" to products,
        "Promoções" to sampleDrinks + sampleCandies,
        "Doces" to sampleCandies,
        "Bebidas" to sampleDrinks
    )
    var text by remember {
        mutableStateOf("")
    }

    fun containsInNameOrDescription() = { product: Product ->
        product.name.contains(
            text,
            ignoreCase = true
        ) ||
                product.description?.contains(
                    text,
                    ignoreCase = true
                ) ?: false
    }

    val searchedProducts = remember(text, products) {
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription()) +
                    products.filter(containsInNameOrDescription())
        } else emptyList()
    }

    val state = remember(products, text) {
        HomeScreenUiState(
            sections = sections,
            searchedProducts = searchedProducts,
            searchText = text,
            onSearchChange = {
                text = it
            }
        )
    }
    HomeScreen(state = state)
}

@Composable
fun HomeScreen(
    state: HomeScreenUiState = HomeScreenUiState()
) {
    Column {
        val text = state.searchText
        val searchedProducts = state.searchedProducts
        val sections = state.sections

        SeachTextField(searchText = text, onSearchChange = state.onSearchChange)

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (state.isShowSections()) {
                sections.forEach { section ->
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { product ->
                    CardProductItem(
                        product,
                        Modifier.padding(horizontal = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(HomeScreenUiState(sections = sampleSections))
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview() {
    HomeScreen(state = HomeScreenUiState(sections = sampleSections, searchText = "a"))
}