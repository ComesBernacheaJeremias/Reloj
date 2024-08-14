package com.example.reloj.ui.theme

import android.util.Log
import androidx.compose.material3.Card
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier


@Composable
fun MiCarta(texto: String, value: Boolean, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Text(text = texto)

        val estado = value
        val checkedState = remember { mutableStateOf(estado) }
        val checked = checkedState.value
        Switch(checked = checkedState.value, onCheckedChange = { checkedState.value = it })
        if (checked) {

        } else {

        }
    }
}