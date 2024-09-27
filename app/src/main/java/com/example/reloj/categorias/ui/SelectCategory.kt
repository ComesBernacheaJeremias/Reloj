package com.example.reloj.categorias.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel


@Composable
fun SelectCategory(categories: Categories, onCategorySelected: (Categories) -> Unit) {
    val checkedState = remember { mutableStateOf(false) }
    Row {
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it
                onCategorySelected(categories)}
            //checkedState.value = it
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterVertically)
        ) {
            Text(text = categories.categoria)

        }
    }

}




/*
@Composable
fun CheckedCategories(categoriesViewModel: CategoriesViewModel) {
    val categorias by categoriesViewModel.obtenerCategorias().observeAsState(emptyList())


    LazyColumn {
        items(categorias) { categories ->
            SelectCategory(categories = categories)
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}
*/