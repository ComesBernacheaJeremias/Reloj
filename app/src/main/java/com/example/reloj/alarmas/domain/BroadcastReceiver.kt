package com.example.reloj.alarmas.domain


import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Build
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import android.provider.Settings
import android.net.Uri
import androidx.compose.material3.Text
import androidx.core.app.NotificationCompat
import com.example.reloj.R
import com.example.reloj.alarmas.data.Alarm

//hace sonar la alarma
class AlarmReceiver : BroadcastReceiver() {

    companion object {
        private var ringtone: Ringtone? = null
        var wakeLock: PowerManager.WakeLock? = null



        fun stopAlarm() {
            ringtone?.stop()
            wakeLock?.release() // Liberar el WakeLock cuando se apague la alarma
            wakeLock = null // Evitar que se intente liberar dos veces

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
        /*val alarmIntent = Intent(context, ActivityAlarmSong::class.java)
        alarmIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK // Importante para iniciar una actividad desde fuera de una actividad
        context.startActivity(alarmIntent)*/

        val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyApp::AlarmWakelockTag")
        wakeLock.acquire(10*60*1000L /*10 minutes*/)

        // Verificar si tiene el permiso de superposición de ventanas
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(context)) {
            val overlayIntent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
            overlayIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(overlayIntent)
        } else {
            val alarmIntent = Intent(context, ActivityAlarmSong::class.java).apply {
                flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or  // Importante para iniciar una actividad desde fuera de una actividad
                            Intent.FLAG_ACTIVITY_CLEAR_TASK or
                            Intent.FLAG_ACTIVITY_SINGLE_TOP // Evita múltiples instancias de la actividad
            }
            context.startActivity(alarmIntent)
        }


    }
}
