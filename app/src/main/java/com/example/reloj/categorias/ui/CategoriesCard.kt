package com.example.reloj.categorias.ui


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.reloj.alarmas.data.Alarm
import com.example.reloj.alarmas.domain.AlarmaViewModel
import com.example.reloj.categorias.data.Categories
import com.example.reloj.categorias.domain.CategoriesViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CategoriesCard(
    categoriesViewModel: CategoriesViewModel,
    alarmaViewModel: AlarmaViewModel,
    categories: Categories,
    onCategorySelected: (Categories) -> Unit
) {


    Card(
        modifier = Modifier
            .height(80.dp)
            .width(150.dp)
            .clickable {
                //que pasa cuando hago click
                onCategorySelected(categories)
            },
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


                        }

                    )
                    if (checked) {
                        //esto actualiza el state de la categoria
                        categoriesViewModel.actualizarCategorias(
                            Categories(
                                categories.categoria,
                                true
                            )
                        )
                    } else {
                        categoriesViewModel.actualizarCategorias(
                            Categories(
                                categories.categoria,
                                false
                            )
                        )
                    }
                    //codigo que actualiza las alarmas segun el state de la categoria
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

                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewCard() {


}