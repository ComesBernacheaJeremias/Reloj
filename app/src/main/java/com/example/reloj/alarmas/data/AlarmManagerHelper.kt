package com.example.reloj.alarmas.data

class AlarmManagerHelper(private val context: Context, private val alarmDao: AlarmDao) {

    fun setAlarms() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager  // Obtiene el servicio del sistema para gestionar alarmas
        val alarms = alarmDao.getAllAlarms().value ?: return  // Obtiene la lista de alarmas de la base de datos; si está vacía, retorna