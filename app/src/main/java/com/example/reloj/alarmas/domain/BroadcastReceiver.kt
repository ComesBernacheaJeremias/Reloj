package com.example.reloj.alarmas.domain


import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.core.app.NotificationCompat
import com.example.reloj.R
import com.example.reloj.alarmas.data.Alarm

//hace sonar la alarma
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
        val hora = intent.getIntExtra("hora", 0)
        val minutos = intent.getIntExtra("minutos", 0)

        Toast.makeText(context, "¡Alarma Sonando! ${hora}: ${minutos}", Toast.LENGTH_LONG).show()
        // Aquí puedes reproducir un sonido, abrir una actividad, etc.
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone  = RingtoneManager.getRingtone(context, alarmUri)
        ringtone?.play()


        // Iniciar una nueva actividad para mostrar la pantalla de alarma
        val alarmIntent = Intent(context, ActivityAlarmSong::class.java)
        alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK // Importante para iniciar una actividad desde fuera de una actividad
        context.startActivity(alarmIntent)

    }
}
