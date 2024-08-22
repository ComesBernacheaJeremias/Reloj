package com.example.reloj.ui.theme

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FloatingButtonAddAlarm() {
    var click = false
    FloatingActionButton(onClick = { click = true }, modifier = Modifier
        .height(150.dp)
        .width(150.dp)) {
        Icon(Icons.Filled.Favorite, "Floating action button.")
    }
    if (click){
        MyTimePicker()
        Log.i("Corcho", "tendria que aparecer")
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyTimePicker(){
    val state = rememberTimePickerState()
    TimePicker(
        state = state,
        modifier = Modifier.padding(15.dp),
        colors = TimePickerDefaults.colors(),
        layoutType = TimePickerDefaults.layoutType()
    )
    Text(text = "Hora seleccionada H:M = ${state.hour} : ${state.minute}")
}