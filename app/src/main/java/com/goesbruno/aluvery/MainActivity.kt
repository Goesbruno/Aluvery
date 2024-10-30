package com.goesbruno.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import com.goesbruno.aluvery.ui.screens.HomeScreen
import com.goesbruno.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            AluveryTheme {
                App()
            }

        }
    }
}


@Composable
fun App() {
    Surface {
        HomeScreen()

    }
}



