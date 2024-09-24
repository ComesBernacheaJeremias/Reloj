package com.example.reloj.alarmas.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//esto es la ENTIDAD
@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // El id es la clave primaria y se genera automáticamente
    @ColumnInfo(name = "hora") val hora: Int,       // Hora en formato 24 horas
    @ColumnInfo(name = "minutos") val minutos: Int,     // Minutes
    @ColumnInfo(name = "state") var state: Boolean, //es para saber si esta o no activado
    //@ColumnInfo(name = "categoria_id") var categoria_id: String //es para saber la categoria
)


//Esto es el DAO (tiene que estar en otro lugar)
@Dao
interface AlarmDao {
    @Insert
    suspend fun insert(alarm: Alarm)  // Función para insertar una nueva alarma en la base de datos


    @Query("SELECT * FROM alarms")
    fun getAllAlarms(): Flow<List<Alarm>> // Devuelve todas las alarmas como un LiveData para observar cambios
    //Puede ser Flow<List<Alarm>>

    @Query("SELECT hora FROM alarms ORDER BY hora ASC")
    fun getHoraAlarms(): Flow<List<Int>>
    @Query("SELECT minutos FROM alarms ORDER BY minutos ASC")
    fun getMinutosAlarms(): Flow<Int>
   /* @Query("SELECT * FROM alarms ORDER BY state ASC")
    fun getStateAlarms(): Flow<Alarm>*/

    @Update
    suspend fun update(alarm: Alarm)


    @Delete
    suspend fun delete(alarm: Alarm)  // Función para eliminar una alarma específica de la base de datos
}

