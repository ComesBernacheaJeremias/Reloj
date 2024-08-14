package com.example.reloj

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.ui.theme.AddCategories
import com.example.reloj.ui.theme.CategoriesCard
import com.example.reloj.ui.theme.MiCarta
import com.example.reloj.ui.theme.RelojTheme


class MainActivity : ComponentActivity() {

    data class Item(val title: String, val description: String, val value: Boolean = false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RelojTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ViewContainer()

                }

            }
        }
    }
}

var nombre1 = "jeremias"


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    val text = remember { mutableStateOf("") }
    Scaffold(
        content = {
            MiUI()
        }
    )
}

@Composable
fun MiUI(text: MutableState<String>? = null) {
    val count by remember { mutableStateOf(text?.value) }


    Log.i("Corcho", "entro en miUI y muestra ${text.toString()}...")
    Log.i("Corcho", "entro en miUI y muestra ${text?.value}...")
    Log.i("Corcho", "entro en miUI y muestra ${count}...")

    Column {

        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                item {

                    CategoriesCard(title = "Todos", text = "Todos", value = false)
                    Spacer(modifier = Modifier.width(4.dp))

                    CategoriesCard(title = nombre1, text = "Desactivado", value = false)
                    Spacer(modifier = Modifier.width(4.dp))
                    CategoriesCard(title = "mañana", text = "Desactivado", value = false)
                    Spacer(modifier = Modifier.width(4.dp))
                    CategoriesCard(title = "Noche", text = "Desactivado", value = false)
                    Spacer(modifier = Modifier.width(4.dp))
                    CategoriesCard(title = "Escuela", text = "Desactivado", value = false)
                    Spacer(modifier = Modifier.width(4.dp))
                }

                    item {
                        val jere = text?.value
                        if (count != null) {
                            CategoriesCard(title = count!!, text = "salio", value = false)
                            Spacer(modifier = Modifier.width(4.dp))
                            Log.i("Corcho", "SIII funciono NOmbre ${text.toString()}, ${jere}")
                        } else {
                            if (text != null) {
                                Log.i(
                                    "Corcho",
                                    "No funciono  ${count}"
                                )
                            }
                            Log.i("Corcho", "No funciono el valor es ${text.toString()}")
                        }


                    }

                item {

                    AddCategories()
                }
            }

        }

        Box(
            modifier = Modifier
                .fillMaxSize() // Ocupa toda la pantalla
                .padding(16.dp) // Agrega un poco de espacio alrededor
        ) {
            Column {
                MiCarta(
                    texto = "Todos", modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth(), value = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                MiCarta(texto = "Mañana", modifier = Modifier.fillMaxWidth(), value = false)
                Spacer(modifier = Modifier.height(16.dp))
                MiCarta(texto = "Nuevo", modifier = Modifier.fillMaxWidth(), value = false)
                // Agrega más tarjetas según sea necesario
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLazyRowContent() {
    RelojTheme {
        //esto no esta afectando. Pero podria utilizar en otro momento
    }
}





