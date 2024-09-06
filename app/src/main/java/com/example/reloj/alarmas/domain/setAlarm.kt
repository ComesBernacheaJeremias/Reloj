package com.example.reloj.alarmas.domain

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar

@SuppressLint("ScheduleExactAlarm")
@Composable
fun SetAlarm(hora:Int, minutos:Int) {
    Log.i("Corcho", "entro en SetAlarm ${hora} ${minutos}")

    val context = LocalContext.current


    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
/*
    val hora = alarmViewModel.hora.value
    val minutos = alarmViewModel.minutos.value
    Log.i("Corcho", " ${hora}, ${minutos}")
*/
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
    Log.i("Corcho", " ${hora}, ${minutos}")

    // Programamos la alarma para que se dispare en la hora exacta
    if (hora != -1 && minutos != -1) {
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
    }else{
        Log.i("Corcho", "NO SE PUDO ACTIVAR")
        Log.i("Corcho", "tengo que mostrar calendar: ${calendar}, ${hora}, ${minutos}")

    }
}
