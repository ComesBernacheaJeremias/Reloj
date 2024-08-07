package com.example.reloj

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.ui.theme.RelojTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RelojTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                        color = MaterialTheme.colorScheme.background
                ){
                    ViewContainer()
                }

            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        content = { LazyRowContent() }
    )
}


@Composable
fun LazyRowContent() {
    LazyRow(modifier = Modifier.padding(8.dp).background(Color.Red)) {
        item {
            Text(
                text = "Hello jerocho!",Modifier.padding(end = 4.dp)
            )
            Text(
                text = "Hello jerocho!",Modifier.padding(end = 4.dp)

            )
            Text(
                text = "Hello jerocho!",Modifier.padding(end = 4.dp)

            )
            Text(
                text = "Hello jerocho!",Modifier.padding(end = 4.dp)

            )
            Text(
                    text = "Hello jerocho!",Modifier.padding(end = 4.dp)

            )
            Text(
            text = "Hello jerocho!",Modifier.padding(end = 4.dp)

        )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun PreviewLazyRowContent() {
    RelojTheme {
        LazyRowContent()
    }
}





