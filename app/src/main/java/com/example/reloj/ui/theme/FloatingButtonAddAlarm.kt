package com.example.reloj.ui.theme

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun FloatingButtonAddAlarm() {
    var showTimePicker by remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { showTimePicker = true },
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
    ) {
        Icon(Icons.Filled.Favorite, contentDescription = "Floating action button")
    }

    if (showTimePicker) {
        MyTimePicker(onDismiss = { showTimePicker = false })
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(onDismiss: () -> Unit = {}) {
    val context = LocalContext.current
    val state = rememberTimePickerState()
    var time by remember { mutableStateOf("") }

    // TimePicker implementation
    TimePicker(
        state = state,
        modifier = Modifier.padding(15.dp),
        colors = TimePickerDefaults.colors(),
        layoutType = TimePickerDefaults.layoutType()
    )

    // Display selected time
    Text(text = "Hora seleccionada H:M = ${state.hour} : ${state.minute}")
    time = "${state.hour} : ${state.minute}"

    // Add buttons to close the TimePicker
    Row {
        Button(onClick = onDismiss) {
            Text("Cerrar")
        }
        Button(onClick = {
            setAlarm(context, state.hour, state.minute)
            onDismiss()
        }) {
            Text("Aceptar")
        }
    }
}

fun setAlarm(context: Context, selectedHour: Int, selectedMinute: Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    // Creamos un Intent para el BroadcastReceiver que manejará la alarma
    val intent = Intent(context, AlarmReceiver::class.java)

    // Creamos un PendingIntent que se activará cuando la alarma suene
    val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    // Configuramos la hora exacta a la que la alarma debe dispararse
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, selectedHour) // Establecemos la hora seleccionada
        set(Calendar.MINUTE, selectedMinute) // Establecemos el minuto seleccionado
        set(Calendar.SECOND, 0) // Aseguramos que los segundos estén en 0
    }

    // Programamos la alarma para que se dispare en la hora exacta
    alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}






@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    MyTimePicker()
}

