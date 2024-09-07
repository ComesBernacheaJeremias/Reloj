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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.Alarm



@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(viewModel: AlarmaViewModel,onDismiss: () -> Unit = {}) {
    // val context = LocalContext.current



        val state = rememberTimePickerState()
        var time by remember { mutableStateOf("") }

    // Obtén el ViewModel dentro de un Composable
    //val nuevoViewModel: AlarmaViewModel = viewModel()
    val context = LocalContext.current
    //val viewModel: AlarmaViewModel = ViewModelProvider(context as Activity).get(AlarmaViewModel::class.java)
    //val viewModel: AlarmaViewModel = viewModel()


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
                        viewModel.insertarAlarmas(Alarm(hora = state.hour, minutos = state.minute, state = true))

                           // AgregarAlarma(nuevoViewModel, hora = state.hour, minutos = state.minute)




                        onDismiss()
                    }) {
                        Text("Aceptar")
                    }

                }
            }
        }


    }




fun AgregarAlarma(
    alarmaViewModel: AlarmaViewModel? = null,
    hora: Int,
    minutos: Int
) {
//ESTO PUEDO SACAR
    if (alarmaViewModel != null) {
        Log.i("Corcho", "ENTRO A LA FUNCION")
        val nuevaAlarma =
            Alarm(hora = hora, minutos = minutos, state = true)
        alarmaViewModel.insertarAlarmas(nuevaAlarma)
        Log.i("Corcho", "se guardo la nueva alarma $nuevaAlarma")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    //MyTimePicker()
}


