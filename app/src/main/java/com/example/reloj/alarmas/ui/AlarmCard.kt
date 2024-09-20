package com.example.reloj.alarmas.ui


import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.domain.AlarmaViewModel


@Composable
fun AlarmCard(
    viewModel: AlarmaViewModel
) {
    //El problema esta en el viewModel. sin este la aplicacion se abre,

    // pero cuando lo uso, se rompe. se rompe al buscar


/*
    val result = viewModel.obtenerAlarmas().value
    val result2 = viewModel.obtenerAlarmas()
    Log.i("Corcho", result.toString())
    Log.i("Corcho", result2.toString())
    val hora by viewModel.obtenerHora().observeAsState()
    Log.i("Corcho", hora.toString())
*/
//este es el OBSERVER DEL LIVE DATA
    val items by viewModel.obtenerAlarmas().observeAsState(emptyList())
    Log.i("Corcho", items.toString())




    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items) { item ->
            ItemCard(item = item)
        }
    }
}

@Composable
fun ItemCard(item: Alarm) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${item.id}")
            Text(text = "Nombre: ${item.hora}")
            Text(text = "Descripción: ${item.minutos}")
        }
    }
}





/*

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Ajusta el padding según sea necesario
    ) {
        Box {
            Row {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(text = "${currentWord} No hay palabra")
                        Text(text = "${hora} No hay palabra")

                    }

                }
            }/*
                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                   // val checkedState = remember { mutableStateOf(value) }
                    val checked = checkedState.value
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            //checkedState.value = it

                        }
                    )
                    if (checked) {
                       // cardAlarmViewModel.ActivarAlarma(hora, minutos)

                    }
                }
                */
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmCard() {

}

*/

