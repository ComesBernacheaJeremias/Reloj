package com.example.reloj.alarmas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reloj.alarmas.data.Alarm
import kotlinx.coroutines.launch

//maneja lo logica relacionada con la interfaz de usuario y el manejo de datos
class AlarmViewModel(private val repository: AlarmRepository) : ViewModel() {

    fun insertarAlarmas(alarm: Alarm) {
        viewModelScope.launch {
            repository.insertar(alarm)
        }
    }

    fun obtenerAlarmas(): LiveData<List<Alarm>> {
        val usuariosLiveData = MutableLiveData<List<Alarm>>()
        viewModelScope.launch {
            usuariosLiveData.value = repository.obtenerTodos()
        }
        return usuariosLiveData
    }
/*
    fun actualizarAlarma(alarm: Alarm) {
        viewModelScope.launch {
            repository.actualizar(alarm)
        }
    }
*/
    fun eliminarAlarma(alarm: Alarm) {
        viewModelScope.launch {
            repository.eliminar(alarm)
        }
    }
}