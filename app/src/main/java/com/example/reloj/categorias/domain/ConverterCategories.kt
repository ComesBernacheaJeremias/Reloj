package com.example.reloj.categorias.domain

import androidx.room.TypeConverter
import com.example.reloj.categorias.data.Categories
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun fromCategory(category: Categories): String {
        // Convertir la categoría a un JSON o a un string para almacenarla en la base de datos
        return Gson().toJson(category)
    }

    @TypeConverter
    fun toCategory(categoryString: String): Categories {
        // Convertir el string (almacenado en la DB) de vuelta a un objeto Categories
        return Gson().fromJson(categoryString, Categories::class.java)
    }
}
