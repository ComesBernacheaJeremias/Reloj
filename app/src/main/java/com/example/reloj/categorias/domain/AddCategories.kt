package com.example.reloj.categorias.domain

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.ui.dialog.DialogCardAddCategories
import com.example.reloj.ui.theme.Apagado
import com.example.reloj.ui.theme.PrimarioCoral

@Composable
fun AddCategories(categoriesViewModel: CategoriesViewModel) {

    val openDialog = remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .height(80.dp)
        .width(150.dp)
        .clickable {
            openDialog.value = true
        },
        colors = CardDefaults.cardColors(
            containerColor = PrimarioCoral
        )

    ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = "Agregar",
                textAlign = TextAlign.Center
            )
        }
    }
    // Muestra el diálogo si openDialog es true
    if (openDialog.value) {
        AlertDialogDoc(openDialog, categoriesViewModel)

    }
}

@Composable
fun AlertDialogDoc(
    openDialog: MutableState<Boolean>,categoriesViewModel: CategoriesViewModel
) {
    var text by rememberSaveable { mutableStateOf("") }
    Log.i("Corcho", "el texto del alertDIalog es ${text}")



    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                openDialog.value = false


            }) {
            DialogCardAddCategories(openDialog,
                onConfirm  = {categoryName->
                    if (categoryName.isNotEmpty()){
                        categoriesViewModel.insertarCategoria(Categories(categoryName, false))
                   }
                    openDialog.value = false // Cierra el diálogo
                    Log.i("Corcho", "el categoriname es ${categoryName}")

                },
                onDismiss = {
                    openDialog.value = false // Cierra el diálogo


                })


        }
    }
}

