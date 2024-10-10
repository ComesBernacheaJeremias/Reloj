package com.example.reloj.categorias.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.AlertDialogDoc
import com.example.reloj.ui.theme.PrimarioCoral

@Composable
fun AllCategoriesCard(allCategorySelected: (Boolean) -> Unit){
    Card(modifier = Modifier
        .height(80.dp)
        .width(150.dp)
        .clickable {
            allCategorySelected(true)
        },
        colors = CardDefaults.cardColors(
            containerColor = PrimarioCoral
        )


    ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = "TODOS",
                textAlign = TextAlign.Center
            )
        }
    }

}