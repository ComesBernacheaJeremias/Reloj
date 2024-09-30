package com.example.reloj.categorias.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.data.CategoriesDao
import kotlinx.coroutines.launch

class CategoriesViewModel(private val dao: CategoriesDao) : ViewModel() {



    fun insertarCategoria(categories: Categories) {
        viewModelScope.launch {
            dao.insert(categories)
        }
    }

    fun obtenerCategorias(): LiveData<List<Categories>> {
        val todasLasCategorias: LiveData<List<Categories>> = dao.getAllCategories().asLiveData()

        return todasLasCategorias
    }
    fun obtenerCategoriasActivadas(): LiveData<List<Categories>>{
        val categoriasActivadas: LiveData<List<Categories>> = dao.getCategoryActivate().asLiveData()

        return categoriasActivadas
    }




    fun actualizarCategorias(categories: Categories) {
        viewModelScope.launch {
            dao.update(categories)
        }
    }

    fun eliminarCategorias(categories: Categories) {
        viewModelScope.launch {
            dao.delete(categories)
        }
    }
}