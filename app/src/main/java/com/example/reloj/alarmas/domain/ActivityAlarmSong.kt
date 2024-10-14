package com.example.reloj.alarmas.domain

import android.app.KeyguardManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


class ActivityAlarmSong  : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // A partir de Android 10 (API 29) usa los nuevos métodos en lugar de los flags obsoletos.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)  // Muestra la actividad cuando el dispositivo está bloqueado
            setTurnScreenOn(true)    // Enciende la pantalla cuando la actividad se muestra

            // Desbloquear el Keyguard (pantalla de bloqueo)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null) // Solicita la eliminación del keyguard


        } else {
            // Para versiones anteriores de Android, utiliza los flags antiguos
            window.addFlags(
                android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                        /*or
                        android.view.WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        android.view.WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON or
                        android.view.WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD*/
            )
        }

        // Contenido de la actividad
        setContent {
            AlarmScreen() // Pantalla que quieres mostrar cuando suene la alarma
        }
    }
}
