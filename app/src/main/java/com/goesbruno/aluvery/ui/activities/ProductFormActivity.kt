package com.goesbruno.aluvery.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.goesbruno.aluvery.dao.ProductDao
import com.goesbruno.aluvery.ui.screens.ProductFormScreen
import com.goesbruno.aluvery.ui.theme.AluveryTheme

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                ProductFormActivity {
                    ProductFormScreen(onSaveClick = { product ->
                        dao.saveProduct(product)
                        finish()
                    })
                }
            }
        }
    }
}


@Composable
fun ProductFormActivity(
    content: @Composable () -> Unit = {}
) {
    Surface {
        Scaffold { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}

