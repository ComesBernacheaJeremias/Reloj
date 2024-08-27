package com.example.reloj.alarmas.ui.dialog


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.reloj.alarmas.domain.AlarmReceiver


@Composable
fun ShowConfirmDialog(onConfirm: () -> Unit, onDismiss: () -> Unit) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
                onDismiss()
            },
            title = { Text(text = "Apagar Alarma") },
            text = { Text("¿Estás seguro de que quieres apagar la alarma?") },
            confirmButton = {
                Button(onClick = {
                    openDialog.value = false
                    onConfirm()  // Acción a tomar al confirmar, por ejemplo, apagar la alarma
                }) {
                    Text("Sí")
                }
            },
            dismissButton = {
                Button(onClick = {
                    openDialog.value = false
                    onDismiss()  // Acción a tomar al cancelar
                }) {
                    Text("No")
                }
            }
        )
    }
}

@Composable
fun AlarmScreeen() {
    val showDialog = remember { mutableStateOf(false) }
    val offAlarm = remember { mutableStateOf(false) }
    if (offAlarm.value) {
        cancelAlarm(context = LocalContext.current)
    }

    // Botón para apagar la alarma
    Button(onClick = { showDialog.value = true }) {
        Text("Apagar Alarma")
    }

    // Mostrar el diálogo de confirmación
    if (showDialog.value) {
        ShowConfirmDialog(
            onConfirm = {
                offAlarm.value = true // Llama a la función para apagar la alarma
                showDialog.value = false
            },
            onDismiss = {
                showDialog.value = false
            }
        )
    }
}

fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val alarmIntent = Intent(context, AlarmReceiver::class.java)

    val pendingIntent =
        PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT)


    /*
    // Cancela la alarma
    alarmManager.cancel(pendingIntent)
    pendingIntent.cancel() // También cancela el PendingIntent

     */
}
