package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.SnackbarDefaults.color
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.ui.componentes.CitasRow
import com.edu.ucne.uniqueapp.ui.componentes.ConfirmationDialog
import com.edu.ucne.uniqueapp.ui.componentes.ListTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasListScreen(
    clienteId: Int = 1,
    navigateUp: () -> Unit,
    onNewCita: () -> Unit, viewModel: CitasViewModel = hiltViewModel(),
    onCitaClick: (Int) -> Unit
) {
    remember {
         viewModel.setCliente(clienteId)
        1
    }
    viewModel.obtenerLista()
    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = onNewCita) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar cita")
            }
        },
        topBar = {
            ListTopBar(label = "Lista de citas", onBackPress = navigateUp)
        },
    ) { paddingValues ->
        val uiState = viewModel.listUiState.collectAsState()
        Spacer(modifier = Modifier.padding(paddingValues))
        var citaId by remember { mutableStateOf(0) }
        var showConfirmation by remember { mutableStateOf(false) }
        if (showConfirmation) {
            ConfirmationDialog(onDismiss = { showConfirmation = false }) {
                viewModel.cancelarCita(citaId)
            }
        }
        CitasListBody({
            citaId = it
            showConfirmation = true
        }, uiState.value.citas) {
            onCitaClick(it)
        }
    }
}
@Composable
fun CitasListBody(onCitaSwipe: (Int) -> Unit, citaList: List<Cita>, onCitaClick: (Int) -> Unit) {
 Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFFFF3F5))
    ) {
        LazyColumn {
            items(citaList) { cita ->
                CitasRow(onCitaSwipe = { onCitaSwipe(it) }, cita = cita) {
                    onCitaClick(it)
                }
            }
        }
    }

}

@Composable
fun CitaList(cita: CitaDto, onCitaClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onCitaClick(cita.citaId) })
        ) {
            Row() {
                Text(
                    text = cita.nombre,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = cita.fecha,
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
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


            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}
