package com.goesbruno.aluvery.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.goesbruno.aluvery.dao.ProductDao
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.ui.states.ProductFormScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.math.BigDecimal

class ProductFormScreenViewModel : ViewModel() {

    private val dao = ProductDao()
    private val _uiState: MutableStateFlow<ProductFormScreenUiState> =
        MutableStateFlow(ProductFormScreenUiState())
    val uiState get() = _uiState.asStateFlow()

    private val regex = "^\\d*(\\.\\d{0,2})?$"

    init {

        _uiState.update { currentState ->
            currentState.copy(
                onUrlChange = { newUrl ->
                    _uiState.value = _uiState.value.copy(
                        url = newUrl
                    )
                },
                onNameChange = { newName ->
                    _uiState.value = _uiState.value.copy(
                        name = newName
                    )
                },
                onPriceChange = { newPrice ->
                    if (newPrice.matches(Regex(regex))) {
                        _uiState.value = _uiState.value.copy(
                            price = newPrice
                        )
                    }
                },
                onDescriptionChange = { newDescription ->
                    _uiState.value = _uiState.value.copy(
                        description = newDescription
                    )
                }
            )
        }

    }

    fun save() {
        _uiState.value.run {
            val convertedPrice = try {
                BigDecimal(price)
            } catch (exception: NumberFormatException) {
                BigDecimal.ZERO
            }
            val product = Product(
                name = name,
                image = url,
                price = convertedPrice,
                description = description
            )
            dao.saveProduct(product)
        }
    }

}