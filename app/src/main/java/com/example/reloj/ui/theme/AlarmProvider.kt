package com.example.reloj.ui.theme

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class AlarmViewModel : ViewModel() {
     var alarmCard = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    fun agregarCarta(nuevaCarta: String) {  //<--estos son los metodos. las acciones que puede realizar.
        alarmCard.add(nuevaCarta)
    }
}