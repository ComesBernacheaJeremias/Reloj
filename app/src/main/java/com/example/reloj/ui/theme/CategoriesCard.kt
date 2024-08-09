package com.example.reloj.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CategoriesCard(title: String, text: String, value: Boolean) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .width(150.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            Column {

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }

}
@Composable
fun AddCategories(){
    Card( modifier = Modifier
        .height(80.dp)
        .width(150.dp)
        .clickable {
            AdderNewCategories()
        }

    ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = "Agregar",
               // modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun AdderNewCategories() {

}
@Composable
fun AllCategories(){

}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {

    AddCategories()
}