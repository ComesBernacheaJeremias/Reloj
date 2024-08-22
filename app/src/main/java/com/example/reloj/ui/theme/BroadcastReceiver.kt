package com.example.reloj.ui.theme


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Acción que ocurre cuando la alarma se dispara

        Toast.makeText(context, "¡Alarma Sonando!", Toast.LENGTH_LONG).show()
        // Aquí puedes reproducir un sonido, abrir una actividad, etc.
    }
}
