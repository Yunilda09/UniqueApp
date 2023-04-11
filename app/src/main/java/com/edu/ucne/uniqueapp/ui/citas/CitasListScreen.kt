package com.edu.ucne.uniqueapp.ui.citas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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
            .fillMaxSize()
            .padding(top = 64.dp)
            .background(Color(0xFFFFEEED))
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
