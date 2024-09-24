package com.example.reloj.alarmas.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.domain.AlarmaViewModel
import com.example.reloj.alarmas.domain.SetAlarm

/*
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
*/


@Composable
fun ItemCard(viewModel: AlarmaViewModel,item: Alarm) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Box {
            Row (modifier = Modifier.fillMaxWidth()) {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(text = "ID: ${item.id}")
                        Text(text = "Nombre: ${item.hora}")
                        Text(text = "Descripción: ${item.minutos}")

                    }

                }

                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    val checkedState = remember { mutableStateOf(item.state) }
                    val checked = checkedState.value
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checkedState.value = it
                            item.state = true

                        }
                    )
                    if (checked) {
                        Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos} esta activada")
                        Log.i("Corcho", "la hora esta ${item.state}")
                        Activado(viewModel, item)


                    } else {
                        Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos}  esta Desactivada ${item}...${item.state}:)")
                        Desactivado(viewModel, item)
                    }
                }
            }
        }
    }
}
@Composable
fun Activado(viewModel: AlarmaViewModel, hora:Alarm){
    Log.i("Corcho", "entro en activado. viewmodel = ${viewModel}....hora = ${hora}")
    viewModel.actualizarAlarma(hora)
    SetAlarm(hora)


}
fun Desactivado(viewModel: AlarmaViewModel, hora:Alarm){
    Log.i("Corcho", "entro en DESACTIVADO. ..hora = ${hora}")
    val updatedHora = Alarm(id = hora.id, hora = hora.hora, minutos = hora.minutos, state = false)
    viewModel.actualizarAlarma(updatedHora)
    Log.i("Corcho", "entro en DESACTIVADO2. ...hora = ${updatedHora}")

}