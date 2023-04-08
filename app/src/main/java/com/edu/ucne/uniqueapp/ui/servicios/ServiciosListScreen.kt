package com.edu.ucne.uniqueapp.ui.servicios

/*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.ui.citas.CitasViewModel
import com.edu.ucne.uniqueapp.ui.componentes.CitasRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiciosListScreen (
    onNewServicio: () -> Unit, viewModel: ServicioViewModel = hiltViewModel(),
    onServicioClick: (Int) -> Unit
){
    viewModel.obtenerLista()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = { Text(text = "Lista De Servicios",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineLarge) }
            )
        },
    ) {
        val uiStateServicio by viewModel.uiStateServicios.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ServiciosListBody({ viewModel.obtenerLista(it) }, uiStateServicio.servicios) {
                onServicioClick(it)
            }
        }
    }
}

@Composable
fun ServiciosListBody( servicioList: List<ServiciosDto>, onServicioClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFFFFF3F5))
    ) {
        LazyColumn {
            items(servicioList) { servicio ->
                CitasRow(  citaDto = servicio) {
                    onServicioClick(it)
                }
            }
        }
    }

}

@Composable
fun ServicioList(servicio: ServiciosDto, onServicioClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onServicioClick(servicio.servicioId) })
        ) {
            Row() {
                Text(
                    text = servicio.descripcion,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = servicio.costo,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )

            }

        }
        Divider(Modifier.fillMaxWidth())
    }
}*/
