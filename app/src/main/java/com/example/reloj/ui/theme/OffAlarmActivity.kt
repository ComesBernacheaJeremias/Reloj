package com.example.reloj.ui.theme

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmScreen() // Mostrar la interfaz de alarma aquí
        }
    }
}
@Composable
fun AlarmScreen() {
    val context = LocalContext.current

    Button(onClick = {
        AlarmReceiver.stopAlarm() // Detiene la alarma
        // Puedes finalizar la actividad si deseas
        (context as? Activity)?.finish()
    }) {
        Text("Detener Alarma")
    }
}