package com.goesbruno.aluvery.dao

import com.goesbruno.aluvery.model.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductDao {

    companion object{
       private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun products(): StateFlow<List<Product>> = products.asStateFlow()

    fun saveProduct(product: Product){
        products.value += product
    }


}