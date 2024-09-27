package com.example.reloj.alarmas.domain




import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.categorias.data.Categories

import com.example.reloj.categorias.ui.checkedCategories


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(viewModel: AlarmaViewModel,onDismiss: () -> Unit = {}) {


        val state = rememberTimePickerState()
        var time by remember { mutableStateOf("") }
        val context = LocalContext.current



    Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .zIndex(1f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // TimePicker implementation
                TimePicker(
                    state = state,
                    modifier = Modifier.padding(15.dp),
                    colors = TimePickerDefaults.colors(),
                    layoutType = TimePickerDefaults.layoutType()
                )
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    // Mostrar la hora seleccionada
                    Text(text = "Hora seleccionada H:M = ${state.hour} : ${state.minute}")
                    time = "${state.hour} : ${state.minute}"
                        checkedCategories(viewModel)
                }


                // Add buttons to close the TimePicker
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cerrar")
                    }
                    Button(onClick = {
                        viewModel.insertarAlarmas(Alarm(hora = state.hour, minutos = state.minute, state = true, categoria =  ))


                        onDismiss()
                    }) {
                        Text("Aceptar")
                    }

                }
            }
        }


    }




@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    //MyTimePicker()
}


