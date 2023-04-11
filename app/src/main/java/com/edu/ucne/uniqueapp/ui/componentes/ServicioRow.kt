package com.edu.ucne.uniqueapp.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.ui.theme.Rosa10

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServicioRow(servicio: ServiciosDto, onServicioClick: (Int) -> Unit) {

    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .clickable(onClick = { onServicioClick(servicio.tipoId) })
                .fillMaxWidth()
        ) {
            Row() {
                Text(
                    text = servicio.descripcion,
                    style = MaterialTheme.typography.titleLarge,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    color = Rosa10,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = servicio.costo.toString(),
                    style = MaterialTheme.typography.titleSmall,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.End,
                    color = Rosa10,
                    modifier = Modifier.weight(3f)
                )
            }
        }
        Divider(Modifier.fillMaxWidth().width(1.dp),
        color = Rosa10)
    }
}
