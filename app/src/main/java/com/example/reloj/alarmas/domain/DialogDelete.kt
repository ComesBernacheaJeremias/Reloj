package com.example.reloj.alarmas.domain

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.reloj.alarmas.data.Alarm


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
