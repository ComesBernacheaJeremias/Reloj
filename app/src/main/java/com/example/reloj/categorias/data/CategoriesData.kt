package com.example.reloj.categorias.data

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update
import com.example.reloj.alarmas.data.Alarm
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "categories_table")
    data class Categories(
    @PrimaryKey(autoGenerate = false) val categoria: String,
    @ColumnInfo(name = "state") val state:Boolean)

@Dao
interface CategoriesDao {
    @Insert
    suspend fun insert(categories: Categories)


    @Query("SELECT * FROM categories_table")
    fun getAllCategories(): Flow<List<Categories>>

    @Query("SELECT * FROM categories_table WHERE state = 1")
    fun getCategoryActivate(): Flow<List<Categories>>
    //Busca categorias por state



    @Update
    suspend fun update(categories: Categories)


    @Delete
    suspend fun delete(categories: Categories)
}