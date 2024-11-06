package com.goesbruno.aluvery.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.goesbruno.aluvery.dao.ProductDao
import com.goesbruno.aluvery.sampleData.sampleSections
import com.goesbruno.aluvery.ui.screens.HomeScreen
import com.goesbruno.aluvery.ui.screens.HomeScreenUiState
import com.goesbruno.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {

    private val dao = ProductDao()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AluveryTheme {
                App(onFabClick = {
                    startActivity(Intent(this, ProductFormActivity::class.java))
                }) {
                    val products = dao.products()
                    HomeScreen(products = products)
                }
            }

        }
    }
}


@Composable
fun App(
    onFabClick: () -> Unit = {},
    content: @Composable () -> Unit = {}
) {
    Surface {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = onFabClick) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = null)
                }
            }
        ) { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                content()
            }
        }
    }
}

@Preview
@Composable
private fun AppPreview() {
    App {
        HomeScreen(HomeScreenUiState(sections = sampleSections))
    }
}