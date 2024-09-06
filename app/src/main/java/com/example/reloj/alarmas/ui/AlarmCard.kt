package com.example.reloj.alarmas.ui

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.reloj.alarmas.data.CambiarViewModel


@Composable
fun AlarmCard(
    title: String,
    text: String,
    value: Boolean,
    modifier: Modifier = Modifier,
    cardAlarmViewModel: CambiarViewModel = viewModel()
) {
    val hora = cardAlarmViewModel.hora.value
    val minutos = cardAlarmViewModel.minutos.value

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp) // Ajusta el padding según sea necesario
    ) {
        Box {
            Row {
                Box {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = title)
                        Text(text = text)

                    }
                }
                Box(modifier = Modifier.align(Alignment.CenterVertically)) {
                    val checkedState = remember { mutableStateOf(value) }
                    val checked = checkedState.value
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checkedState.value = it

                        }
                    )
                    if (checked) {
                        cardAlarmViewModel.ActivarAlarma(hora, minutos)
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewAlarmCard() {
    AlarmCard(
        title = "Alarm Title",
        text = "This is a description of the alarm.",
        value = true, // O false para ver el estado desactivado
        modifier = Modifier.padding(16.dp)
    )
}