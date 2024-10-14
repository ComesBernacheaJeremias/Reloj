package com.example.reloj

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
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
import androidx.compose.material.icons.filled.AddCircle
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
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
//import com.example.reloj.categorias.domain.selectAlarmByCategory
import com.example.reloj.categorias.ui.AllCategoriesCard
import com.example.reloj.ui.theme.Fondo
import com.example.reloj.ui.theme.PrimarioCoral
import com.example.reloj.ui.theme.RelojTheme
import android.provider.Settings
import android.widget.Toast
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.example.reloj.ui.theme.Transparente


class MainActivity : ComponentActivity() {

    companion object {
        const val REQUEST_CODE_OVERLAY_PERMISSION = 1001 // Definir la constante aquí
        const val REQUEST_CODE_POST_NOTIFICATIONS = 1002
    }

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

        // Solicita el permiso de notificaciones en Android 13+
        fun requestNotificationsPermission() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_CODE_POST_NOTIFICATIONS)
                }
            }
        }

        // Solicita el permiso de superposición de ventanas
        fun requestOverlayPermission() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                (this as Activity).startActivityForResult(
                    intent,
                    REQUEST_CODE_OVERLAY_PERMISSION)
            }
        }

        // Solicitar permisos
        requestNotificationsPermission()
        requestOverlayPermission()


        // Solicitar el permiso para notificaciones en Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1001)
            }
            if (!Settings.canDrawOverlays(this)) {

                // Si el permiso no está habilitado, mostramos un mensaje y redirigimos al usuario a la configuración
                Toast.makeText(
                    this,
                    "Se necesita permiso para mostrar la alarma sobre otras apps.",
                    Toast.LENGTH_LONG
                ).show()

                // Redirige al usuario a la pantalla de ajustes donde puede habilitar el permiso
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:${this.packageName}")
                )
                // Inicia la actividad con el intent para que el usuario habilite el permiso
                (this as Activity).startActivityForResult(
                    intent,
                    REQUEST_CODE_OVERLAY_PERMISSION)
            }
        }




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
                containerColor = PrimarioCoral, // Color de fondo del FAB
                contentColor = Color.White, // Color del ícono o contenido dentro del FAB
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .zIndex(0f)

            ) {
                Icon(Icons.Filled.AddCircle, contentDescription = "Floating action button")
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
    var categorySelected by remember { mutableStateOf<Categories?>(null) }
    var allCategorySelected by remember { mutableStateOf(true) }







    Column(modifier = Modifier.fillMaxSize(). padding(4.dp)) {
        LazyRow {
            item {
                AllCategoriesCard(allCategorySelected = { selectedCategory ->
                    allCategorySelected = selectedCategory
                })
                Spacer(modifier = Modifier.width(4.dp))
            }
            items(categorias) { categorias ->
                CategoriesCard(categoriesViewModel, alarmViewModel, categorias, onCategorySelected = { selectedCategory ->
                    categorySelected = selectedCategory
                    allCategorySelected = false
                })
                Spacer(modifier = Modifier.width(4.dp))
            }
            item { AddCategories(categoriesViewModel) }

        }
        if (categorySelected != null && !allCategorySelected){
            //CODIGOO...acá deberia ir la funcion que busque las alarmas con categorias = categorySelected (tengo que modificar el Dao del ROOM)
            //Tambien debo corroborar que el state de la categoria sea true
            Log.i("CorchoC", "la categoria esta seleccionada ${categorySelected}")
             val alarmByCategories by alarmViewModel.obtenerPorCategorias(categorySelected!!.categoria).observeAsState(emptyList())

            Box(
                modifier = Modifier
                    .weight(1f) // Ocupa toda la pantalla
                    .padding(16.dp) // Agrega un poco de espacio alrededor

            ) {
                Column {
                    LazyColumn(modifier = Modifier
                        .weight(1f)) {
                        items(alarmByCategories) { alarmas ->
                            ItemCard(alarmViewModel, item =alarmas) }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .background(Fondo)){
                    }
                }
            }



        }else {
            Log.i("CorchoC", "la categoria esta DESseleccionada${categorySelected}")

            Box(
                modifier = Modifier
                    .weight(1f) // Ocupa toda la pantalla
                    .padding(16.dp) // Agrega un poco de espacio alrededor

            ) {
                Column {
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        items(alarmas) { alarmas ->
                            ItemCard(alarmViewModel, item = alarmas)
                        }
                        item {
                            Spacer(modifier = Modifier.height(4.dp))
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Fondo)
                    ) {

                    }
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






