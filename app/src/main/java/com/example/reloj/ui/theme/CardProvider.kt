package com.example.reloj.ui.theme

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartasViewModel : ViewModel() {
    // Usamos `mutableStateListOf` para crear una lista observable por Compose.
    var cartas = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    fun agregarCarta(nuevaCarta: String) {
        cartas.add(nuevaCarta)
    }
}