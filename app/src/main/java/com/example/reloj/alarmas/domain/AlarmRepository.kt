package com.example.reloj.alarmas.domain

import androidx.lifecycle.LiveData
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao

//Intermediario entre Base de Datos y ViewModel
class AlarmRepository(private val alarmDao: AlarmDao) {

    suspend fun insertar(alarma: Alarm) {
        alarmDao.insert(alarma)
    }

    suspend fun obtenerTodos(): List<Alarm> {
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