package com.example.reloj.alarmas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Base de datos
@Database(entities = [Alarm::class], version = 1)
abstract class AlarmDatabase : RoomDatabase() {
    abstract val alarmDao: AlarmDao

}
/*
    companion object {
        @Volatile
        private var INSTANCE: AlarmDatabase? = null

        fun getDatabase(context: Context): AlarmDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, AlarmDatabase::class.java, "alarm_database")
                    .build().also { INSTANCE = it }
            }
        }
    }

 */


