package com.example.reloj.alarmas.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.AlarmViewModel
import java.util.Calendar

@Composable
fun setAlarm(context: Context, alarmViewModel: AlarmViewModel = viewModel()) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    var hora = alarmViewModel.hora.value
    var minutos = alarmViewModel.minutos.value

    // Creamos un Intent para el BroadcastReceiver que manejará la alarma
    val intent = Intent(context, AlarmReceiver::class.java)


    // Creamos un PendingIntent que se activará cuando la alarma suene
    val pendingIntent =
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    // Configuramos la hora exacta a la que la alarma debe dispararse
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hora) // Establecemos la hora seleccionada
        set(Calendar.MINUTE, minutos) // Establecemos el minuto seleccionado
        set(Calendar.SECOND, 0) // Aseguramos que los segundos estén en 0
    }

    // Programamos la alarma para que se dispare en la hora exacta
    if (hora != -1 && minutos != -1) {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }else{
        Toast.makeText(applicationContext,
            "Codigo ya registrado. Pruebe con otro",
            Toast.LENGTH_SHORT
        ).show()

    }
}
