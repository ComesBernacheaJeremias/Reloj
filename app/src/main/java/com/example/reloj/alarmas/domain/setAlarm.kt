package com.example.reloj.alarmas.domain

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.reloj.alarmas.data.Alarm
import java.util.Calendar

@SuppressLint("ScheduleExactAlarm")
@Composable
fun SetAlarm(alarm: Alarm) {
    Log.i("Corcho", "entro en SetAlarm ${alarm.hora} ${alarm.minutos}")

    val context = LocalContext.current


    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val hora = alarm.hora
    val minutos = alarm.minutos
    Log.i("Corcho", " ${hora}, ${minutos}")

    // Creamos un Intent para el BroadcastReceiver que manejará la alarma
    val intent = Intent(context, AlarmReceiver::class.java)
    intent.putExtra("hora", hora)
    intent.putExtra("minutos", minutos)


    // Creamos un PendingIntent que se activará cuando la alarma suene
    val pendingIntent =
        PendingIntent.getBroadcast(context, alarm.id, intent, PendingIntent.FLAG_UPDATE_CURRENT)

    // Configuramos la hora exacta a la que la alarma debe dispararse
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hora) // Establecemos la hora seleccionada
        set(Calendar.MINUTE, minutos) // Establecemos el minuto seleccionado
        set(Calendar.SECOND, 0) // Aseguramos que los segundos estén en 0
    }
    Log.i("Corcho", "la alarma sonara  ${hora}, ${minutos}")
    if (calendar.timeInMillis <= System.currentTimeMillis()) {
        // Si la hora ya pasó, programamos la alarma para el día siguiente
        calendar.add(Calendar.DAY_OF_YEAR, 1)
        Log.i("Corcho_Alarma", "la hora ya paso, pasa para el dia siguiente $hora : $minutos")
    }

    if (alarm.state) {
        // Programamos la alarma para que se dispare en la hora exacta
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        Log.i("Corcho", "se guardo la alarma $calendar")
    }else{
        Log.i("Corcho", "NO SE PUDO ACTIVAR")
        Log.i("Corcho", "tengo que mostrar calendar: ${calendar}, ${hora}, ${minutos}")

    }
}
