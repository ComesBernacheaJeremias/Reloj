package com.example.reloj.alarmas.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // El id es la clave primaria y se genera automáticamente
    val hora: Int,       // Hora en formato 24 horas
    val minutos: Int,     // Minutes
    var state:Boolean //es para saber si esta o no activado
)
// Clase que maneja los usuarios
class GestorAlarm {
    private val alarmas = mutableMapOf<Int, Alarm>()

    // Agrega un usuario
    fun agregarAlarma(alarma: Alarm) {
        alarmas[alarma.hora] = alarma

    }
}

@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: Alarm)  // Función para insertar una nueva alarma en la base de datos


    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): LiveData<List<Alarm>>  // Devuelve todas las alarmas como un LiveData para observar cambios

    @Delete
    suspend fun delete(alarm: Alarm)  // Función para eliminar una alarma específica de la base de datos
}
//Base de datos
@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao
}