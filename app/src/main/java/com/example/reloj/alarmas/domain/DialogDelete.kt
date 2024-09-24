package com.example.reloj.alarmas.domain

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
    fun alertDialogDoc() {
        val openDialog = remember { mutableStateOf(true) }

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                title = {
                    Text(text = "Desea eliminar esta alarma?")
                },
                text = {
                    Text(
                        "Descripción de la Alarma (capaz no)"
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            //poner codigo de eliminar
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
