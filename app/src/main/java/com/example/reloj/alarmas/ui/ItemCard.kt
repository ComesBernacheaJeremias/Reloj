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
import com.example.reloj.alarmas.data.Alarm

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
fun ItemCard(item: Alarm) {
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

                        }
                    )
                    if (checked) {
                        Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos} esta activada")

                    } else {
                        Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos}  esta Desactivada :)")
                    }
                }
            }
        }
    }
}