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
         return dao.getAllAlarms()
    }

    fun obtenerHora() {
        dao.getHoraAlarms()
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