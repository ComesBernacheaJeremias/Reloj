package com.example.reloj.alarmas.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.AlarmViewModel
import java.util.*

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(alarmViewModel: AlarmViewModel = viewModel(), onDismiss: () -> Unit = {}) {
    val context = LocalContext.current
    val state = rememberTimePickerState()
    var time by remember { mutableStateOf("") }
    var goToSetAlarm by remember {
        mutableStateOf(false)
    }
    if (goToSetAlarm){setAlarm(context)}

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
                    goToSetAlarm = true
                    alarmViewModel.agregarCarta(time)
                    alarmViewModel.newHora(state.hour)
                    alarmViewModel.newMinutos(state.minute)
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

