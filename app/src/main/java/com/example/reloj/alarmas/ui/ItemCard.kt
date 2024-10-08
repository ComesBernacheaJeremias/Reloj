package com.example.reloj.alarmas.ui

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
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
            }
    ) {
        Box {
            Row (modifier = Modifier.fillMaxWidth()) {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Text(text = "ID: ${item.id}")
                        Text(text = "Nombre: ${item.hora}")
                        Text(text = "Descripción: ${item.minutos}")
                        Text(text = "Categoria: ${item.categoria}")
                        Text(text = "Estado: ${item.state}")

                    }

                }
               /* Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    // Estado de la alarma, inicialmente configurado a false
                    val activarAlarma = remember { mutableStateOf(item.state) }

                    if (activarAlarma.value) {
                        Activado(viewModel, item)
                    }

                    // Switch que controla el estado de la alarma
                    Switch(
                        checked = activarAlarma.value,  // Usa activarAlarma para manejar el estado visible del Switch
                        onCheckedChange = { isChecked ->  // Recibe el nuevo estado del Switch
                            activarAlarma.value = isChecked  // Actualiza el estado del Switch

                            // Log y actualizaciones dependiendo del estado de la alarma
                            if (isChecked) {
                                Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos} está activada")
                                viewModel.actualizarAlarma(
                                    Alarm(
                                        id = item.id,
                                        hora = item.hora,
                                        minutos = item.minutos,
                                        state = true,
                                        categoria = item.categoria
                                    )
                                )
                            } else {
                                Log.i("Corcho", "La alarma ${item.hora} : ${item.minutos} está desactivada")
                                viewModel.actualizarAlarma(
                                    Alarm(
                                        id = item.id,
                                        hora = item.hora,
                                        minutos = item.minutos,
                                        state = false,
                                        categoria = item.categoria
                                    )
                                )
                                Desactivado(viewModel, item)
                            }
                        }
                    )
                }*/


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
                          }


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
    SetAlarm(viewModel, hora)


}
fun Desactivado(viewModel: AlarmaViewModel, hora:Alarm){
    Log.i("Corcho", "entro en DESACTIVADO. ..hora = ${hora}")
    val updatedHora = Alarm(id = hora.id, hora = hora.hora, minutos = hora.minutos, state = false, categoria = hora.categoria)
    viewModel.actualizarAlarma(updatedHora)
    Log.i("Corcho", "entro en DESACTIVADO2. ...hora = ${updatedHora}")

}