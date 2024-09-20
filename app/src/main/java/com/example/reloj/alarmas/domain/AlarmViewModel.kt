package com.example.reloj.alarmas.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//maneja lo logica relacionada con la interfaz de usuario y el manejo de datos
class AlarmaViewModel(private val dao: AlarmDao) : ViewModel() {



    fun insertarAlarmas(alarm: Alarm) {
        viewModelScope.launch {
            dao.insert(alarm)
        }
    }

    fun obtenerAlarmas(): LiveData<List<Alarm>>{
        val todasLasAlarmas: LiveData<List<Alarm>> = dao.getAllAlarms().asLiveData()

        return todasLasAlarmas
    }

    fun obtenerHora(): LiveData<List<Int>> {
        val hora: LiveData<List<Int>> = dao.getHoraAlarms().asLiveData()
        return hora
    }



    /*
        fun obtenerAlarmas(): MutableLiveData<Flow<Alarm>> {
            val usuariosLiveData = MutableLiveData<Flow<Alarm>>()
            viewModelScope.launch {
                usuariosLiveData.value = dao.getAllAlarms()
            }
            return usuariosLiveData
        }

     */
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

sealed interface ContactEvent {
    data class Hora(val hora: String) : ContactEvent
    data class Minutos(val minut: String) : ContactEvent

}