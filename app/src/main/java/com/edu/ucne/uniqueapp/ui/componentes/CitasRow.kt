package com.edu.ucne.uniqueapp.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import kotlin.math.absoluteValue


@Composable
fun CitasRow(onCitaSwipe:(Int) -> Unit, citaDto: CitaDto, onCitaClick: (Int) -> Unit) {
    var offset by remember { mutableStateOf(0f) }

   Card(modifier = Modifier
       .fillMaxWidth()
       .padding(8.dp)
       .clickable { onCitaClick(citaDto.citaId) }
       .scrollable(orientation = Orientation.Horizontal, state = rememberScrollableState { scroll ->
           offset += scroll
           if (offset.absoluteValue > 49) {
                if(citaDto.estadoId == 1 )
                    onCitaSwipe(citaDto.citaId)
           }
           scroll
       }),
       colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer
       )
   ) {
       Column(modifier = Modifier.padding(2.dp)) {
           Row(horizontalArrangement = Arrangement.SpaceBetween) {
               Text(text = citaDto.nombre+" "+citaDto.apellido)
               Text(text = citaDto.fecha.substring(0, 10))

           }
           Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = citaDto.servicioId.toString())
                Text(text = citaDto.fecha.substring(11,16))
           }
       }

   }
}