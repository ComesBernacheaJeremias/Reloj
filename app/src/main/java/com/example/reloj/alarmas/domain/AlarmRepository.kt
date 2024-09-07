package com.example.reloj.alarmas.domain

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDatabase




//Intermediario entre Base de Datos y ViewModel-----private val alarmDao: AlarmDao
open class AlarmRepository(context: Context) {

    private val database = AlarmDatabase.getDatabase(context).alarmDao()


    suspend fun insertar(alarma: Alarm) {
        database.insert(alarma)
    }

    suspend fun obtenerTodos(): List<Alarm> {
        return database.getAllAlarms()
    }
/*
    suspend fun actualizar(alarma: Alarm) {
        alarmDao.(alarma)
    }*/

    suspend fun eliminar(alarma: Alarm) {
        database.delete(alarma)
    }
}