package com.example.reloj.categorias.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

//las clases son tipos nuevos de datos (objetos)

class CartasViewModel : ViewModel() {
    // Usamos `mutableStateListOf` para crear una lista observable por Compose.

    //Estas son las propiedades (atributos) que definen al objeto:
    var cartas = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    fun agregarCarta(nuevaCarta: String) {  //<--estos son los metodos. las acciones que puede realizar.
        cartas.add(nuevaCarta)
    }
}