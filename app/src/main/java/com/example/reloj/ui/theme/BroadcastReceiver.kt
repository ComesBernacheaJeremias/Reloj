package com.example.reloj.ui.theme


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.Composable

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private var ringtone: Ringtone? = null

        fun stopAlarm() {
            ringtone?.stop()
        }
    }




    override fun onReceive(context: Context, intent: Intent) {
        // Acción que ocurre cuando la alarma se dispara
        //el BOOT_COMPLETED es para que la alarma no se pierda si se reinicia el dispositivo
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm here.
        }

        Toast.makeText(context, "¡Alarma Sonando!", Toast.LENGTH_LONG).show()
        // Aquí puedes reproducir un sonido, abrir una actividad, etc.
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone  = RingtoneManager.getRingtone(context, alarmUri)
        ringtone?.play()


        // Iniciar una nueva actividad para mostrar la pantalla de alarma
        val alarmIntent = Intent(context, AlarmActivity::class.java)
        alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // Importante para iniciar una actividad desde fuera de una actividad
        context.startActivity(alarmIntent)

    }
}
