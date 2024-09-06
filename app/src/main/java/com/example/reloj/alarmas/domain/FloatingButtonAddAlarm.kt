package com.example.reloj.alarmas.domain



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.Alarm


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(nuevoViewModel: AlarmaViewModel = viewModel(), onDismiss: () -> Unit = {}) {
    // val context = LocalContext.current



        val state = rememberTimePickerState()
        var time by remember { mutableStateOf("") }




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
                        val nuevaAlarma =
                            Alarm(hora = state.hour, minutos = state.minute, state = true)
                        nuevoViewModel.insertarAlarmas(nuevaAlarma)
                        Log.i("Corcho", "se guardo la nueva alarma $nuevaAlarma")


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
    MyTimePicker()
}


