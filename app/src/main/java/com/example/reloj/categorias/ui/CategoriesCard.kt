package com.example.reloj.categorias.ui


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel


@Composable
fun CategoriesCard(categoriesViewModel: CategoriesViewModel, categories: Categories, onCategorySelected: (Categories) -> Unit) {


    Card(
        modifier = Modifier
            .height(80.dp)
            .width(150.dp)
            .clickable {
                //que pasa cuando hago click
                onCategorySelected(categories)
            },
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            Column {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categories.categoria,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val checkedState = remember { mutableStateOf(false) }
                    val checked = checkedState.value

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checkedState.value = it


                        }

                    )
                    if (checked) {
                        categoriesViewModel.actualizarCategorias(Categories(categories.categoria, true))
                    }else{  categoriesViewModel.actualizarCategorias(Categories(categories.categoria, false))}

                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {


}