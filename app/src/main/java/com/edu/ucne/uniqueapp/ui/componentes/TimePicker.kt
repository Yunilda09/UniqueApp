package com.edu.ucne.uniqueapp.ui.componentes

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePickerDialog(
    label: String,
    onSelectTime: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        TimePicker(label = label, onSelectTime = { onSelectTime(it) }) {
            onDismissRequest()
        }
    }
}

var currentHour = 1
var currentMinute = 1

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TimePicker(
    label: String,
    onSelectTime: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 5.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(24.dp))

            val chosenHour = remember { mutableStateOf(LocalTime.now().hour) }
            val chosenMinutes = remember { mutableStateOf(LocalTime.now().minute) }

            DateSelectionSection(
                onHourChosen = { chosenHour.value = it.toInt() },
                onMinuteChosen = { chosenMinutes.value = it.toInt() }
            )
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {
                    if (chosenHour.value > 9) {
                        if (chosenMinutes.value > 9) {
                            onSelectTime("${chosenHour.value}:${chosenMinutes.value}")
                        } else {
                            onSelectTime("${chosenHour.value}:0${chosenMinutes.value}")
                        }
                    } else {
                        if (chosenMinutes.value > 9) {
                            onSelectTime("0${chosenHour.value}:${chosenMinutes.value}")
                        } else {
                            onSelectTime("0${chosenHour.value}:0${chosenMinutes.value}")
                        }
                    }
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Confirmar",
                    color = Color.Black,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DateSelectionSection(
    onHourChosen: (String) -> Unit,
    onMinuteChosen: (String) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
    ) {
        val h = 23

        val horas = List(h) { (it + 1).toString() }
        Column(horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(text = "Hora")
            InfiniteItemsPicker(
                items = horas,
                firstIndex = Int.MAX_VALUE / 2 + (currentHour),
                onItemSelected = onHourChosen
            )
        }
        val m = 59

        val minutos = List(m) { (it + 1).toString() }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Minuto")
            InfiniteItemsPicker(
                items = minutos,
                firstIndex = Int.MAX_VALUE / 2 - 7 + currentMinute,
                onItemSelected = onMinuteChosen
            )
        }

    }
}

@SuppressLint("FrequentlyChangedStateReadInComposition")
@Composable
fun InfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = Modifier.height(130.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE, itemContent = {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(3.dp))

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(3.dp))
                })
            }
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun Preview() {
    TimePicker(label = "Seleccione la hora", onSelectTime = {}) {

    }
}