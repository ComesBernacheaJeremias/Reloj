package com.example.reloj.categorias.ui


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.domain.AlarmaViewModel
import com.example.reloj.alarmas.domain.AlertDialogCategoryDelete
import com.example.reloj.alarmas.domain.AlertDialogDelete
import com.example.reloj.alarmas.domain.CancelarAlarma
import com.example.reloj.alarmas.domain.ProgramarAlarmas
import com.example.reloj.alarmas.domain.SetAlarm
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel
import com.example.reloj.ui.theme.Apagado
import com.example.reloj.ui.theme.Fondo3
import com.example.reloj.ui.theme.PrimarioCoral
import com.example.reloj.ui.theme.SecundarioFuerte


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CategoriesCard(
    categoriesViewModel: CategoriesViewModel,
    alarmaViewModel: AlarmaViewModel,
    categories: Categories,
    onCategorySelected: (Categories) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    if (showDialog){
        AlertDialogCategoryDelete(categoriesViewModel, alarmaViewModel, categories)
    }


    Card(
        colors = CardDefaults.cardColors(
            containerColor = PrimarioCoral),
        modifier = Modifier
            .height(80.dp)
            .width(150.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {onCategorySelected(categories)},
                    onLongPress = { showDialog = true }
                )
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            Column {

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = categories.categoria,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    val checkedState = remember { mutableStateOf(categories.state) }
                    val checked = checkedState.value

                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checkedState.value = it


                        },colors = SwitchDefaults.colors(
                            checkedThumbColor = SecundarioFuerte, // Color del thumb cuando está encendido
                            checkedTrackColor = Fondo3, // Color del track cuando está encendido
                            uncheckedThumbColor = Apagado, // Color del thumb cuando está apagado
                            uncheckedTrackColor = Fondo3 // Color del track cuando está apagado
                        )

                    )
                    if (checked) {
                        //esto actualiza el state de la categoria
                        categoriesViewModel.actualizarCategorias(
                            Categories(
                                categories.categoria,
                                true
                            )
                        )

                        //codigo que actualiza las alarmas segun el state de la categoria (Creo que no sirve para nada)
                        if (categories.state) {
                            alarmaViewModel.actualizarAlarmaPorCategoria(categories.categoria, true)
                            Log.i("actualiz", "actualizo a true")

                        } else {
                            alarmaViewModel.actualizarAlarmaPorCategoria(
                                categories.categoria,
                                false
                            )
                            Log.i("actualiz", "NO actualizo")
                        }


                        val alarmasParaActivar by alarmaViewModel.obtenerPorCategorias(categories.categoria)
                            .observeAsState(emptyList())
                        // Iteramos sobre la lista de alarmas
                        if (alarmasParaActivar.isNotEmpty()) {
                            for (alarm in alarmasParaActivar) {
                                Log.i("Corcho", "entro en el for")
                                SetAlarm(alarm)
                            }
                        } else {
                            Log.i("Corcho", "No hay alarmas para activar")
                        }
                        //ProgramarAlarmas(alarmasParaActivar)
                    } else {
                        categoriesViewModel.actualizarCategorias(
                            Categories(
                                categories.categoria,
                                false
                            )
                        )
                        //codigo que actualiza las alarmas segun el state de la categoria (Creo que no sirve para nada)
                        alarmaViewModel.actualizarAlarmaPorCategoria(
                            categories.categoria,
                            false
                        )
                        Log.i("actualiz", "NO actualizo")

                        val alarmasParaActivar by alarmaViewModel.obtenerPorCategorias(
                            categories.categoria
                        ).observeAsState(emptyList())
                        if (alarmasParaActivar.isNotEmpty()) {
                            for (alarm in alarmasParaActivar) {
                                Log.i("Corcho", "entro en el for para cancelar")
                                CancelarAlarma(alarm.id)
                            }

                        } else {
                            Log.i("Corcho", "La lista de alarmas para cancelar esta bacia")
                        }


                    }


                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {


}