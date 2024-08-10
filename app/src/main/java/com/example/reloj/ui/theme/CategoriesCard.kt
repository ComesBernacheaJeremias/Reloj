package com.example.reloj.ui.theme


import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog


@Composable
fun CategoriesCard(title: String, text: String, value: Boolean) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .width(150.dp),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),

            ) {
            Column {

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(
                        text = text,
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )

                }
            }
        }
    }

}

@Composable
fun AddCategories() {

    val openDialog = remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .height(80.dp)
        .width(150.dp)
        .clickable { openDialog.value = true }

    ) {
        Box(modifier = Modifier.fillMaxSize(), Alignment.Center) {
            Text(
                text = "Agregar",
                // modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center
            )
        }
    }
    // Muestra el diálogo si openDialog es true
    if (openDialog.value) {
        alertDialogDoc(openDialog)
    }
}

@Composable
fun alertDialogDoc(openDialog: MutableState<Boolean>) {
    val openDialog = remember { mutableStateOf(true) }
    var text by rememberSaveable { mutableStateOf("") }

    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            }) {
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
                    TextField(
                        value = text,
                        onValueChange = { text = it },
                        placeholder = { Text("Nombre") },
                        label = { Text(text = "Nueva Categoria") },
                        singleLine = true
                    )
                    Button(onClick = { AdderNewCategories(text = text) }) {
                        Text("Add Category")
                        
                    }
                }

            }

        }
    }
}


@Composable
fun AdderNewCategories(text: String) {
    val texto = text
    Log.i("Corcho", "lo que aparece en addernewcategories es ${texto}")

}

@Composable
fun AllCategories() {

}

@Preview(showBackground = true)
@Composable
fun PreviewCard() {

}