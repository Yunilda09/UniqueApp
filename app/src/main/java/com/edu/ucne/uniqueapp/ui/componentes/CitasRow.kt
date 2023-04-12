package com.edu.ucne.uniqueapp.ui.componentes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.ui.theme.Rosa10
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@Composable
fun CitasRow(
    onCitaSwipe: (Int) -> Unit,
    cita: Cita,
    onCitaClick: (Int) -> Unit
) {
    val offsetY = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .clickable { onCitaClick(cita.id) }
        .offset {
            IntOffset(offsetY.value.roundToInt(), 0)
        }
        .draggable(
            state = rememberDraggableState { delta ->
                coroutineScope.launch {
                    offsetY.snapTo(offsetY.value + delta)
                }
            },
            orientation = Orientation.Horizontal,
            onDragStopped = {
                coroutineScope.launch {
                    offsetY.animateTo(
                        targetValue = 0f,
                        animationSpec = tween(
                            durationMillis = 1000,
                            delayMillis = 100
                        )
                    )
                    if (cita.estadoId == 1)
                        onCitaSwipe(cita.id)
                }
            }
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFEEED)
        )
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .padding(horizontal = 4.dp)
                .fillMaxWidth()
        ) {
            if(cita.estadoId!=1)
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = cita.estado,
                        style = MaterialTheme.typography.titleSmall,
                        color = Rosa10
                    )
                }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = cita.cliente,
                    style = MaterialTheme.typography.titleMedium,
                    color = Rosa10
                )
                Text(
                    text = cita.fecha,
                    color = Rosa10
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
            ) {
                Text(
                    text = cita.servicio,
                    style = MaterialTheme.typography.titleSmall,
                    color = Rosa10
                )
                Text(
                    text = cita.hora,
                    color = Rosa10
                )
            }
        }
    }
}