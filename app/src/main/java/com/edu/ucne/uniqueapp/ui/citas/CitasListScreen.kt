package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.SnackbarDefaults.color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasListScreen (
    onNewCita: () -> Unit, viewModel: CitasViewModel = hiltViewModel(),
    onCitaClick: (Int) -> Unit
){
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista De Citas",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge) }
            )
        },
    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CitasListBody(uiState.citas) {
                onCitaClick(it)
            }
        }
    }
}

@Composable
fun CitasListBody(citaList: List<CitaDto>, onCitaClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()
        .background(Color(0xFFFFF3F5))
       // color = Color(0xFFC45559)
    ) {
        LazyColumn {
            items(citaList) { ticket ->
                CitaRow(ticket) {
                    onCitaClick(it)
                }
            }
        }
    }

}

@Composable
fun CitaRow(cita: CitaDto, onCitaClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                //.clickable(onClick = { onCitaClick(cita.citaId) })
        ) {
                Row() {
                    Text(
                        text = cita.nombre,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(3f)
                    )
                   /* Text(
                        text = cita.fecha,
                        style = MaterialTheme.typography.titleSmall,
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(3f)
                    )*/
                    Text(
                        text = cita.apellido,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(3f)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                   /* Text(
                        text = cita.hora,
                        style = MaterialTheme.typography.titleMedium,
                    )*/

                }
            }
            Divider(Modifier.fillMaxWidth())
        }
}
