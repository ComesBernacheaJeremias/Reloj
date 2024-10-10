package com.example.reloj.alarmas.domain

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.reloj.ui.theme.Fondo
import com.example.reloj.ui.theme.PrimarioCoral
import com.example.reloj.ui.theme.SecundarioFuerte


class AlarmActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmScreen() // Mostrar la interfaz de alarma aquí
        }
    }
}
/*
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
}*/
@Preview
@Composable
fun AlarmScreen() {
    val context = LocalContext.current

    Box(modifier = androidx.compose.ui.Modifier
            .fillMaxSize()
            .background(Fondo)
            .padding(16.dp),
        contentAlignment = Alignment.Center // Centrar el contenido en el medio
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "La alarma está sonando",
                style = MaterialTheme.typography.titleLarge,
                color = SecundarioFuerte,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón flotante para detener la alarma
            FloatingActionButton(
                onClick = {
                    AlarmReceiver.stopAlarm() // Detiene la alarma
                    (context as? Activity)?.finish() // Finaliza la actividad si es necesario
                },
                containerColor = SecundarioFuerte, // Cambia el color del botón
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Detener Alarma"
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Presiona para detener la alarma",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
    }
}