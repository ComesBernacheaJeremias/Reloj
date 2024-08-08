package com.example.reloj

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.ui.theme.RelojTheme


class MainActivity : ComponentActivity() {
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
                ){
                    ViewContainer()

                }

            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        content = {
            MiUI()}
    )
}

@Composable
fun LazyRowContent() {
    Log.i("Corcho", "Debería entrar en LazyRowContent")
    LazyRow(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth() // Asegura que LazyRow ocupe todo el ancho disponible
    ) {
        // Usar item en lugar de items si solo hay un conjunto fijo de elementos
        item {
            Row { // Envolver en Row para alinear las tarjetas horizontalmente
                Card(modifier = Modifier
                    .size(70.dp)
                    .padding(end = 8.dp) // Añadir espaciado entre tarjetas
                ) {
                    Text(text = "Todos", modifier = Modifier.padding(8.dp))
                }
                Card(modifier = Modifier
                    .size(70.dp)
                    .padding(end = 8.dp)
                ) {
                    Text(text = "Mañana", modifier = Modifier.padding(8.dp))
                }
                Card(modifier = Modifier
                    .size(70.dp)
                    .padding(end = 8.dp)
                ) {
                    Text(text = "Nuevo", modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun MiCarta(texto: String, value: Boolean, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(text = texto)

        val estado = value
        Log.i("Corcho", "cambie un boton ${estado}, quiero saber que dice")
        val checkedState = remember { mutableStateOf(estado) }
        val checked = checkedState.value
        Log.i("Corcho", "lo que aparece en checked es${checked}")
        Switch(checked = checkedState.value, onCheckedChange = {checkedState.value = it} )
        if (checked){
            Log.i("Corcho", "el valor es verdadero, ${checked}")
            LazyRowContent()
        }else{Log.i("Corcho", "el valor es falso ${checked}")}
    }
}
@Composable
fun MiUI() {
    Column {

        Box(
            modifier = Modifier
                .height(50.dp)
                .padding(16.dp)
        ) {
            Row {
                Text(text = "Hola")
                Text(text = "Chau")
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

    }
}





