package com.example.reloj.alarmas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//maneja lo logica relacionada con la interfaz de usuario y el manejo de datos
class AlarmaViewModel(private val dao: AlarmDao) : ViewModel() {

   /* // Para exponer los datos de alarmas como LiveData
    private val _alarmas = MutableLiveData<List<Alarm>>()
    val alarmas: LiveData<List<Alarm>> get() = _alarmas
*/
    fun insertarAlarmas(alarm: Alarm) {
        viewModelScope.launch {
            dao.insert(alarm)
        }
    }

    fun obtenerAlarmas(): MutableLiveData<Flow<Alarm>> {
        val usuariosLiveData = MutableLiveData<Flow<Alarm>>()
        viewModelScope.launch {
            usuariosLiveData.value = dao.getAllAlarms()
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
            dao.delete(alarm)
        }
    }
}