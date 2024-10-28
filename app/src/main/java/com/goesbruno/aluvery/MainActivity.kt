package com.goesbruno.aluvery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.goesbruno.aluvery.ui.theme.AluveryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AluveryTheme {
                Surface() {
                    MyFirstComposable()

                }
            }

        }
    }
}

@Composable
fun MyFirstComposable() {
    Text(text = "My First Composable")
}

@Preview(showBackground = true)
@Composable
fun MyFirstComposablePreview() {
    AluveryTheme {
        MyFirstComposable()
    }
}