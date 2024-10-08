package com.example.reloj.alarmas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.data.CategoriesDao
import com.example.reloj.categorias.domain.Converters

//Base de datos
@Database(entities = [Alarm::class, Categories::class], version = 1)
@TypeConverters(Converters::class)
abstract class AlarmDatabase : RoomDatabase() {
    abstract val alarmDao: AlarmDao
    abstract val categoriesDao: CategoriesDao

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


