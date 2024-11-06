package com.goesbruno.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.goesbruno.aluvery.R
import com.goesbruno.aluvery.model.Product
import com.goesbruno.aluvery.ui.theme.AluveryTheme
import java.math.BigDecimal


class ProductFormScreenUiState(
    val url: String = "",
    val name: String = "",
    val price: String = "",
    val description:String = "",
    val isShowPreview: Boolean = url.isNotBlank(),
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {}
)


@Composable
fun ProductFormScreen(
    onSaveClick: (Product) -> Unit = {}
) {
    var url by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var price by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    val regex = "^\\d*(\\.\\d{0,2})?$"

    ProductFormScreen(
        state = ProductFormScreenUiState(
            url = url,
            name = name,
            price = price,
            description = description,
            onUrlChange = { newUrl ->
                url = newUrl
            },
            onNameChange = {newName ->
                name = newName
            },
            onPriceChange = {newPrice ->
                if (newPrice.matches(Regex(regex))){
                    price = newPrice
                }
            },
            onDescriptionChange = {newDesc ->
                description = newDesc
            }
        ),
        onSaveClick = {
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
            onSaveClick(product)
        }
    )

}



@Composable
fun ProductFormScreen(
    state: ProductFormScreenUiState = ProductFormScreenUiState(),
    onSaveClick: () -> Unit = {}
) {
    val url = state.url
    val name = state.name
    val price = state.price
    val description = state.description

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Spacer(Modifier)
        Text(
            "Criando o produto",
            Modifier.fillMaxWidth(),
            fontSize = 28.sp
        )


        if (state.isShowPreview) {
            AsyncImage(
                model = url, contentDescription = null,
                Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.placeholder)
            )
        }
        TextField(
            value = url, onValueChange = state.onUrlChange,
            Modifier.fillMaxWidth(),
            label = { Text("Url da imagem") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Uri,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = name, onValueChange = state.onNameChange,
            Modifier.fillMaxWidth(),
            label = { Text("Nome") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                capitalization = KeyboardCapitalization.Sentences
            )
        )



        TextField(
            value = price, onValueChange = state.onPriceChange,
            Modifier.fillMaxWidth(),
            label = { Text("Preço") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        TextField(
            value = description, onValueChange = state.onDescriptionChange,
            Modifier
                .fillMaxWidth()
                .heightIn(min = 100.dp),
            label = { Text("Descrição") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )


        Button(
            onClick = onSaveClick,
            Modifier.fillMaxWidth()
        ) {
            Text("Salvar")
        }
        Spacer(Modifier)
    }
}


@Preview
@Composable
fun ProductFormScreenPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(state = ProductFormScreenUiState())
        }
    }
}
@Preview
@Composable
fun ProductFormScreenFilledPreview() {
    AluveryTheme {
        Surface {
            ProductFormScreen(
                state = ProductFormScreenUiState(
                    url = "url teste",
                    name = "nome teste",
                    price = "123",
                    description = "descrição teste"
                )
            )
        }
    }
}