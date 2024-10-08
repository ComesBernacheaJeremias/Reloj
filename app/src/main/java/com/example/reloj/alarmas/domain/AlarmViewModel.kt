package com.example.reloj.alarmas.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao
import com.example.reloj.categorias.data.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

//maneja lo logica relacionada con la interfaz de usuario y el manejo de datos
class AlarmaViewModel(private val dao: AlarmDao) : ViewModel() {


    fun insertarAlarmas(alarm: Alarm) {
        viewModelScope.launch {
            dao.insert(alarm)
        }
    }

    fun obtenerAlarmas(): LiveData<List<Alarm>> {
        val todasLasAlarmas: LiveData<List<Alarm>> = dao.getAllAlarms().asLiveData()

        return todasLasAlarmas
    }

    fun obtenerPorCategorias(horaCategory: String): LiveData<List<Alarm>> {
        val alarmByCategory: LiveData<List<Alarm>> =
            dao.getHoraAlarmsByCategory(horaCategory).asLiveData()
        return alarmByCategory
    }
    /*fun actualizarListaDeAlarmas(alarm:List<Alarm>){
        viewModelScope.launch {
            dao.updateByCategory(alarm)
        }
    }*/

    fun obtenerHora(): LiveData<List<Int>> {
        val hora: LiveData<List<Int>> = dao.getHoraAlarms().asLiveData()
        return hora
    }


    fun actualizarAlarma(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.update(alarm)
        }
        //agregue el dispatchers.IO
    }
    fun actualizarAlarmaPorCategoria(categoria: String, nuevoEstado: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            val filasActualizadas = dao.actualizarEstadoAlarmaPorCategoria(categoria, nuevoEstado)
            Log.i("actualiz", "Filas actualizadas: $filasActualizadas")
        }
    }



    fun eliminarAlarma(alarm: Alarm) {
        viewModelScope.launch {
            dao.delete(alarm)
        }
    }
}

