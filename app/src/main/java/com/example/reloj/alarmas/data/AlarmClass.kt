package com.example.reloj.alarmas.data

data class Alarm(
   // val id: Int,         // Identificador único para cada alarma
    val hora: Int,       // Hora en formato 24 horas
    val minutos: Int     // Minutos
)
// Clase que maneja los usuarios
class GestorAlarm {
    private val alarmas = mutableMapOf<Int, Alarm>()

    // Agrega un usuario
    fun agregarAlarma(alarma: Alarm) {
        alarmas[alarma.hora] = alarma

    }
}