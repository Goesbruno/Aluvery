package com.goesbruno.aluvery.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.goesbruno.aluvery.R
import com.goesbruno.aluvery.extensions.toBrazilianCurrency
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.sampleData.sampleProducts
import com.goesbruno.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal

@Composable
fun CardProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    elevation: Dp = 4.dp
){
    Card(
        modifier
            .fillMaxWidth()
            .heightIn(150.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        )
    ) {
        Column {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                placeholder = painterResource(id = R.drawable.placeholder),
                contentScale = ContentScale.Crop
            )
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.inversePrimary)
                    .padding(16.dp)
            ) {
                Text(
                    text = product.name
                )
                Text(
                    text = product.price.toBrazilianCurrency()
                )
            }
            product.description?.let {
                Text(
                    text = product.description,
                    Modifier
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardProductItemPreview(){
    AluveryTheme {
        CardProductItem(
            product = sampleProducts[0]
        )
    }
}