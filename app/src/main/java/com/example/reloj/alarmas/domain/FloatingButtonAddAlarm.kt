package com.example.reloj.alarmas.domain




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel
import com.example.reloj.categorias.ui.SelectCategory
import com.example.reloj.ui.theme.Fondo

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(viewModel: AlarmaViewModel, categoriesViewModel: CategoriesViewModel,onDismiss: () -> Unit = {}) {


    val state = rememberTimePickerState()
    var time by remember { mutableStateOf("") }
    //val context = LocalContext.current
    val categorias by categoriesViewModel.obtenerCategorias().observeAsState(emptyList())
    var categorySelected by remember { mutableStateOf<Categories?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // TimePicker implementation
            TimePicker(
                state = state,
                modifier = Modifier.padding(15.dp),
                colors = TimePickerDefaults.colors(),
                layoutType = TimePickerDefaults.layoutType()
            )

            // Mostrar la hora seleccionada
            Text(
                text = "Hora seleccionada H:M = ${state.hour} : ${state.minute}",
                modifier = Modifier.padding(vertical = 8.dp), color = Color.White
            )
            time = "${state.hour} : ${state.minute}"

            // Mostrar las categorías debajo del TimePicker
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .background(Fondo)
            ) {
                items(categorias) { categories ->
                    SelectCategory(
                        categories = categories,
                        onCategorySelected = { selectedCategory ->
                            categorySelected = selectedCategory
                        }
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }

            // Add buttons to close the TimePicker
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = onDismiss) {
                    Text("Cerrar")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    if (categorySelected != null) {
                        viewModel.insertarAlarmas(
                            Alarm(
                                hora = state.hour,
                                minutos = state.minute,
                                state = true,
                                categoria = categorySelected!!.categoria
                            )
                        )
                    }
                    onDismiss()
                }) {
                    Text("Aceptar")
                }
            }
        }
    }



/*
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .zIndex(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // TimePicker implementation
            TimePicker(
                state = state,
                modifier = Modifier.padding(15.dp),
                colors = TimePickerDefaults.colors(),
                layoutType = TimePickerDefaults.layoutType()
            )
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                // Mostrar la hora seleccionada
                Text(text = "Hora seleccionada H:M = ${state.hour} : ${state.minute}")
                time = "${state.hour} : ${state.minute}"
                LazyColumn {
                    items(categorias) { categories ->
                        SelectCategory(
                            categories = categories,
                            onCategorySelected = { selectedCategory ->
                                categorySelected = selectedCategory
                            }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                }


                // Add buttons to close the TimePicker
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = onDismiss) {
                        Text("Cerrar")
                    }
                    Button(onClick = {
                        if (categorySelected != null) {
                        viewModel.insertarAlarmas(
                            Alarm(
                                hora = state.hour,
                                minutos = state.minute,
                                state = true,
                                categoria = categorySelected!!.categoria
                                //Acá elijo el nombre de la categoria
                            )
                        )}


                        onDismiss()
                    }) {
                        Text("Aceptar")
                    }

                }
            }
        }


    }*/
}


/*

@Preview(showBackground = true)
@Composable
fun PreviewContent() {
    //MyTimePicker()
}
*/

