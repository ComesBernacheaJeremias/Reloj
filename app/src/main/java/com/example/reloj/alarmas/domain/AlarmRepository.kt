package com.example.reloj.alarmas.domain

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao
import com.example.reloj.alarmas.data.AlarmDatabase
import kotlinx.coroutines.flow.Flow

/*
//Intermediario entre Base de Datos y ViewModel-----private val alarmDao: AlarmDao-------lo que estaba en parametro para el database es context:Context
open class AlarmRepository(private val alarmDao: AlarmDao) {

    //private val database = AlarmDatabase.getDatabase(context).alarmDao()


    suspend fun insertar(alarma: Alarm) {
        alarmDao.insert(alarma)
    }

    suspend fun obtenerTodos(): Flow<Alarm> {
        return alarmDao.getAllAlarms()
    }
/*
    suspend fun actualizar(alarma: Alarm) {
        alarmDao.(alarma)
    }*/

    suspend fun eliminar(alarma: Alarm) {
        alarmDao.delete(alarma)
    }
}
*/
