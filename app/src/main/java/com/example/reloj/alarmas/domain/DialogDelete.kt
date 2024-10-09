package com.example.reloj.alarmas.domain

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel


@Composable
    fun AlertDialogDelete(viewModel: AlarmaViewModel, alarm: Alarm) {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "¿Desea eliminar esta alarma ${alarm.hora}: ${alarm.minutos}?")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            //poner codigo de eliminar
                            viewModel.eliminarAlarma(alarm)
                            openDialog.value = false
                        }
                    ) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                        }
                    ) {
                        Text("Salir")
                    }
                }
            )
        }
    }

@Composable
fun AlertDialogCategoryDelete(categoriesViewModel: CategoriesViewModel, alarmaViewModel: AlarmaViewModel, categories: Categories) {
    val openDialog = remember { mutableStateOf(true) }
    val alarmasParaEliminar by alarmaViewModel.obtenerPorCategorias(categories.categoria)
        .observeAsState(emptyList())

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            title = {
                Text(text = "¿Desea eliminar  ${categories.categoria} y sus alarmas?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        Log.i("Eliminar", "se esta por eliminar $alarmasParaEliminar")
                        //poner codigo de eliminar
                        alarmaViewModel.eliminarAlarmaPorCategoria(alarmasParaEliminar)
                        categoriesViewModel.eliminarCategorias(categories)
                        openDialog.value = false
                        Log.i("Eliminar", "se elimino $alarmasParaEliminar")
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("Salir")
                }
            }
        )
    }
}
