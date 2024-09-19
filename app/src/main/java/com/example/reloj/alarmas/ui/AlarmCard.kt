package com.example.reloj.alarmas.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.data.AlarmDao
import com.example.reloj.alarmas.domain.AlarmaViewModel


@Composable
fun AlarmCard(
    viewModel: AlarmaViewModel
) {
    //El problema esta en el viewModel. sin este la aplicacion se abre,
    // pero cuando lo uso, se rompe. se rompe al buscar

   //var corcho = viewModel.obtenerAlarmas()
    //val corcho2 = viewModel.obtenerHora()
   // val corcho3 = viewModel.obtenerHora().toString()


    //Log.i("Corcho", "corcho")
   // Log.i("Corcho", "${corcho2.value}")
    //Log.i("Corcho", corcho3)
    //Log.i("Corcho", viewModel.obtenerHora().toString())


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp) // Ajusta el padding según sea necesario
    ) {
        Box {
            Row {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {
  /*                      Text(text = viewModel.obtenerAlarmas().toString())
                        Log.i("Corcho", viewModel.obtenerAlarmas().toString())
                        Text(text = "minutos.toString()")
                        Text(text = viewModel.obtenerHora().toString())
*/
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
}


@Preview(showBackground = true)
@Composable
fun PreviewAlarmCard() {

}



