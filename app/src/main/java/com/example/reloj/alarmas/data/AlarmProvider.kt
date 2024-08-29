package com.example.reloj.alarmas.data


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.reloj.alarmas.domain.SetAlarm


class AlarmViewModel : ViewModel() {
    var alarmCard = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    private val _alarms = mutableStateListOf<Alarm>()
    val alarms: List<Alarm> get() = _alarms



    var activationAlarm = mutableStateOf(true)
        private set

    var hora = mutableStateOf( -1)
        private set
    var minutos = mutableStateOf( -1)
        private set




    fun agregarCarta(nuevaAlarma: String) {  //<--estos son los metodos. las acciones que puede realizar.
        alarmCard.add(nuevaAlarma)
    }

    fun setActivation(activate: Boolean) {
        activationAlarm.value = activate
    }

    fun newHora(nuevaHora: Int) {
        hora.value = nuevaHora
    }

    fun newMinutos(nuevosMinutos: Int) {
        minutos.value = nuevosMinutos
    }

    @Composable
    fun ActivarAlarma(hora:Int, minutos:Int){
        SetAlarm(hora, minutos)
    }

}
