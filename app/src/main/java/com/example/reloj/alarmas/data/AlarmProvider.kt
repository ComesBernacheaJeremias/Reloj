package com.example.reloj.alarmas.data


import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.reloj.alarmas.domain.SetAlarm


class AlarmViewModel : ViewModel() {
    var alarmCard = mutableStateListOf("Carta 1", "Carta 2", "Carta 3")
        private set

    private val _alarms = mutableStateListOf<Alarm>()
    val alarms: List<Alarm> get() = _alarms



    var activationAlarm = mutableStateOf(true)
        private set

    var hora = mutableStateOf( -1)
        private set
    var minutos = mutableStateOf( -1)
        private set




    fun agregarCarta(nuevaAlarma: String) {  //<--estos son los metodos. las acciones que puede realizar.
        alarmCard.add(nuevaAlarma)
    }

    fun setActivation(activate: Boolean) {
        activationAlarm.value = activate
    }

    fun newHora(nuevaHora: Int) {
        hora.value = nuevaHora
    }

    fun newMinutos(nuevosMinutos: Int) {
        minutos.value = nuevosMinutos
    }

    @Composable
    fun ActivarAlarma(hora:Int, minutos:Int){
        SetAlarm(hora, minutos)
    }

}

@Entity(tableName = "task_entity")
data class TaskEntity (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String = "",
    var isDone:Boolean = false
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_entity")
    fun getAllTasks(): MutableList<TaskEntity>
}
@Database(entities = arrayOf(TaskEntity::class), version = 1)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}

