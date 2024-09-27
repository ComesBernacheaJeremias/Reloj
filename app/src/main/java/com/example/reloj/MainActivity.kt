package com.example.reloj

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDatabase
import com.example.reloj.alarmas.domain.AlarmaViewModel
import com.example.reloj.categorias.domain.AddCategories
import com.example.reloj.categorias.data.CartasViewModel
import com.example.reloj.categorias.ui.CategoriesCard
import com.example.reloj.alarmas.domain.MyTimePicker
import com.example.reloj.alarmas.ui.ItemCard
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel
import com.example.reloj.categorias.ui.AllCategoriesCard
import com.example.reloj.ui.theme.RelojTheme


class MainActivity : ComponentActivity() {

    private val db by lazy {
        Room.databaseBuilder(applicationContext, AlarmDatabase::class.java, "alarm_database")
            .build()
    }

    private val alarmViewModel by viewModels<AlarmaViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return AlarmaViewModel(db.alarmDao) as T
                }
            }
        }
    )
    private val viewModelCategories by viewModels<CategoriesViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CategoriesViewModel(db.categoriesDao) as T
                }
            }
        }
    )









    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Esto conecta con el ViewModel


        setContent {
            RelojTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewContainer(alarmViewModel, viewModelCategories)


                }

            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ViewContainer(alarmViewModel: AlarmaViewModel, categoriesViewModel: CategoriesViewModel) {
    var showTimePicker by remember { mutableStateOf(false) }


    Scaffold(
        floatingActionButton = {

            FloatingActionButton(
                onClick = { showTimePicker = true },
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .zIndex(0f)

            ) {
                Icon(Icons.Filled.Favorite, contentDescription = "Floating action button")
            }


        },
        floatingActionButtonPosition = FabPosition.Center,
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                MiUI(alarmViewModel, categoriesViewModel)


            }
            if (showTimePicker) {


                MyTimePicker(alarmViewModel,categoriesViewModel,onDismiss = { showTimePicker = false })
            }
        }
    )

}


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun MiUI(
    alarmViewModel: AlarmaViewModel,
    categoriesViewModel: CategoriesViewModel,
    cartasViewModel: CartasViewModel = viewModel()


) {
    val cartas = cartasViewModel.cartas
    val alarmas by alarmViewModel.obtenerAlarmas().observeAsState(emptyList())
    val categorias by categoriesViewModel.obtenerCategorias().observeAsState(emptyList())
    Log.i("Corcho", alarmas.toString())







    Column {
        LazyRow {
            item {
                AllCategoriesCard()
                Spacer(modifier = Modifier.width(4.dp))
            }
            items(categorias) { categorias ->
                CategoriesCard(categoriesViewModel, categorias)
                Spacer(modifier = Modifier.width(4.dp))
            }
            item { AddCategories(categoriesViewModel) }

        }

        Box(
            modifier = Modifier
                .weight(1f) // Ocupa toda la pantalla
                .padding(16.dp) // Agrega un poco de espacio alrededor

        ) {
            Column {
                LazyColumn(modifier = Modifier
                    .weight(1f)) {
                    items(alarmas) { alarmas ->
                        ItemCard(alarmViewModel, item =alarmas) }
                    item {
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Red)){
                    Text(text = "oliwis")
                }
            }
        }
    }

    /*
        @Preview(showBackground = true)
        @Composable
        fun PreviewLazyRowContent() {
            RelojTheme {
                //esto no esta afectando. Pero podria utilizar en otro momento
            }
        }*/
}






