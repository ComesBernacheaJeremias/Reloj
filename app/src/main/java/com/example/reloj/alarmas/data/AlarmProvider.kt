package com.example.reloj.alarmas.data

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AlarmViewModel : ViewModel() {
    var alarmCard = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    var activationAlarm = mutableStateOf(true)
        private set

    var hora = mutableStateOf( -1)
        private set
    var minutos = mutableStateOf( -1)
        private set


    fun agregarCarta(nuevaAlarma: String) {  //<--estos son los metodos. las acciones que puede realizar.
        alarmCard.add(nuevaAlarma)
    }

    fun changeDesactivar() {
        activationAlarm.value = false
    }

    fun changeActivar() {
        activationAlarm.value = true
    }

    fun newHora(nuevaHora: Int) {
        hora.value = nuevaHora
    }

    fun newMinutos(nuevosMinutos: Int) {
        minutos.value = nuevosMinutos
    }

}