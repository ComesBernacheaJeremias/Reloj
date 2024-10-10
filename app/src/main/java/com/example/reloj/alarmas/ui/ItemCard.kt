package com.example.reloj.alarmas.ui

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.domain.AlarmaViewModel
import com.example.reloj.alarmas.domain.AlertDialogDelete
import com.example.reloj.alarmas.domain.SetAlarm
import com.example.reloj.categorias.data.Categories
import com.example.reloj.ui.theme.Apagado
import com.example.reloj.ui.theme.Fondo3
import com.example.reloj.ui.theme.PrimarioCoral
import com.example.reloj.ui.theme.SecundarioFuerte


@Composable
fun ItemCard(viewModel: AlarmaViewModel,item: Alarm) {

    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialogDelete(viewModel, item)}
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = { showDialog = true }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = PrimarioCoral
        )
    ) {
        Box {
            Row (modifier = Modifier.fillMaxWidth()) {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(text = "ID: ${item.id}")
                        Text(text = "Hora: ${item.hora}")
                        Text(text = "Minutos: ${item.minutos}")
                        Text(text = "Categoria: ${item.categoria}")
                        Text(text = "Estado: ${item.state}")

                    }

                }

                  Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                      val activarAlarma = remember {
                          mutableStateOf(false)
                      }
                      if (activarAlarma.value) {
                          Activado(viewModel, item)
                      }
                      Switch(
                          checked = item.state,
                          onCheckedChange = {
                              if (item.state) {
                                  Log.i(
                                      "Corcho",
                                      "La alarma ${item.hora} : ${item.minutos} esta activada"
                                  )
                                  Log.i("Corcho", "la hora esta ${item.state}")
                                  viewModel.actualizarAlarma(
                                      Alarm(
                                          id = item.id,
                                          hora = item.hora,
                                          minutos = item.minutos,
                                          state = false,
                                          categoria = item.categoria
                                      )
                                  )
                                  activarAlarma.value = true


                              } else {
                                  item.state = true
                                  activarAlarma.value = false
                                  viewModel.actualizarAlarma(
                                      Alarm(
                                          id = item.id,
                                          hora = item.hora,
                                          minutos = item.minutos,
                                          state = true,
                                          categoria = item.categoria
                                      )
                                  )
                                  Log.i(
                                      "Corcho",
                                      "La alarma ${item.hora} : ${item.minutos}  esta Desactivada ${item}...${item.state}:)"
                                  )
                                    Desactivado(viewModel, item)
                              }
                          },colors = SwitchDefaults.colors(
                              checkedThumbColor = SecundarioFuerte, // Color del thumb cuando está encendido
                              checkedTrackColor = Fondo3, // Color del track cuando está encendido
                              uncheckedThumbColor = Apagado, // Color del thumb cuando está apagado
                              uncheckedTrackColor = Fondo3 // Color del track cuando está apagado
                          ),


                      )
                  }


                }
            }
        }
    }

@Composable
fun Activado(viewModel: AlarmaViewModel, hora:Alarm){
    Log.i("Corcho", "entro en activado. viewmodel = ${viewModel}....hora = ${hora}")
    viewModel.actualizarAlarma(hora)



}
fun Desactivado(viewModel: AlarmaViewModel, hora:Alarm){
    Log.i("Corcho", "entro en DESACTIVADO. ..hora = ${hora}")
    val updatedHora = Alarm(id = hora.id, hora = hora.hora, minutos = hora.minutos, state = false, categoria = hora.categoria)
    viewModel.actualizarAlarma(updatedHora)
    Log.i("Corcho", "entro en DESACTIVADO2. ...hora = ${updatedHora}")

}