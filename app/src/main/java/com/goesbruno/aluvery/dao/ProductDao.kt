package com.goesbruno.aluvery.dao

import androidx.compose.runtime.mutableStateListOf
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.sampleData.sampleProducts

class ProductDao {

    companion object{
       private val products = mutableStateListOf(*sampleProducts.toTypedArray())
    }

    fun products() = products.toList()

    fun saveProduct(product: Product){
        products.add(product)
    }

    fun removeProduct(product: Product){
        products.remove(product)
    }

}