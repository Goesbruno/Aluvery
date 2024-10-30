package com.goesbruno.aluvery.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.goesbruno.aluvery.R
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.sampleData.sampleProducts
import java.math.BigDecimal

@Composable
fun ProductsSection(
    title: String,
    products: List<Product>
) {

    Column {

        Text(
            text = title,
            Modifier.padding(
                start = 16.dp,
                end = 16.dp
            ),
            fontSize = 20.sp,
            fontWeight = FontWeight(400)
        )
        Row(
            Modifier
                .padding(
                    top = 8.dp
                )
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(16.dp)

        ) {
            Spacer(Modifier)
            products.forEach { product ->
                ProductItem(product)
            }
            Spacer(Modifier)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductsSectionPreview() {
    ProductsSection("Promoções", sampleProducts)

}

