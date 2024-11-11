package com.goesbruno.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.goesbruno.aluvery.sampleData.sampleSections
import com.goesbruno.aluvery.ui.components.CardProductItem
import com.goesbruno.aluvery.ui.components.ProductsSection
import com.goesbruno.aluvery.ui.components.SeachTextField
import com.goesbruno.aluvery.ui.states.HomeScreenUiState
import com.goesbruno.aluvery.ui.viewmodels.HomeScreenViewModel




@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel
) {
    val state by viewModel.uiState.collectAsState()
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