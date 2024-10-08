package com.example.reloj.categorias.ui.dialog


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.reloj.MiUI


@SuppressLint("UnrememberedMutableState")
@Composable
fun DialogCardAddCategories(
    openDialog: MutableState<Boolean>,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit
) {
    var text by rememberSaveable { mutableStateOf("") }
    Log.i("Corcho", "el texto es ${text}")

    val maxChars = 10


    Box() {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(modifier = Modifier.weight(2f)) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(20.dp)
                                .fillMaxWidth()
                                .padding(8.dp)
                        ) {
                            IconButton(
                                onClick = {
                                    openDialog.value = false
                                    onDismiss()


                                },
                                modifier = Modifier
                                    .size(50.dp)
                                    .align(Alignment.TopEnd),
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color.LightGray,
                                    contentColor = Color.Red,

                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Close,
                                    contentDescription = "Home"
                                )
                            }
                        }


                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                        ) {


                            TextField(
                                value = text,
                                onValueChange = {
                                    if (it.length <= maxChars) {
                                        text = it  // Solo actualiza el valor si está dentro del límite
                                    }},
                                placeholder = { Text("Nombre") },
                                label = { Text(text = "Nueva Categoria") },
                                singleLine = true,
                                modifier = Modifier.align(Alignment.TopCenter)

                            )
                        }
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    Button(onClick = {
                        onConfirm(text)
                    }) {
                        Text("Add Category")

                    }

                }
            }
        }

    }
}
