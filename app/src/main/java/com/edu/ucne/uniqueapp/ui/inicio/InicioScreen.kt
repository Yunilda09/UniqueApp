package com.edu.ucne.uniqueapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.R
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.ui.componentes.CitasRow
import com.edu.ucne.uniqueapp.ui.componentes.ConfirmationDialog
import com.edu.ucne.uniqueapp.ui.componentes.Menu
import com.edu.ucne.uniqueapp.ui.componentes.NavItem
import com.edu.ucne.uniqueapp.ui.inicio.InicioViewModel
import com.edu.ucne.uniqueapp.ui.theme.Green80
import com.edu.ucne.uniqueapp.ui.theme.Rosa10

@Composable
fun InicioScreen(
    id: Int,
    navItems: List<NavItem>,
    viewModel: InicioViewModel = hiltViewModel(),
    onVerMisCitasClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCitaClick: (Int) -> Unit
) {
    remember {
        viewModel.setUsuario(id)
        0
    }

    Menu(navItems = navItems) {
        val uiState by viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFFFFF3F5))
        ) {
            Image(
                painter = painterResource(id = R.drawable.uniqueapp),
                contentDescription = "header",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Date un tiempo para ti!",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFFC45559)
            )
            Spacer(modifier = Modifier.padding(8.dp))
            Inicio(viewModel = viewModel, onVerMisCitasClick, onSaveClick) {
                onCitaClick(it)
            }
        }

    }
}

@Composable
fun Inicio(
    viewModel: InicioViewModel,
    onVerMisCitasClick: () -> Unit,
    onSaveClick: () -> Unit,
    onCitaClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Button(
            onClick = onSaveClick,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFEDEE2),
                contentColor = Color(0xFFC45559)
            )
        ) {
            Text(text = "Crear cita")
        }
        Button(
            onClick = onVerMisCitasClick,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Green80,
                contentColor = Color(0xFFC45559)
            )
        ) {
            Text(text = "Ver mis citas")
        }
        val uiState = viewModel.listUiState.collectAsState()
        var idCita by remember { mutableStateOf(0) }
        viewModel.getCitasProximas()
        var showConfirmation by remember { mutableStateOf(false) }
        if (showConfirmation) {
            ConfirmationDialog(onDismiss = { showConfirmation = false }) {
                viewModel.cancelarCita(idCita)
            }
        }
        CitasProximas(listCitas = uiState.value.citas,
            onCitaSwipe = {
                idCita = it
                showConfirmation = true
            },
            onCitaClick = { onCitaClick(it) })
    }
}

@Composable
fun CitasProximas(
    listCitas: List<Cita>,
    onCitaSwipe: (Int) -> Unit,
    onCitaClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = "Proximas Citas",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Rosa10
        )
        listCitas.forEach {
            CitasRow(onCitaSwipe = onCitaSwipe, cita = it, onCitaClick = onCitaClick)
        }

    }
}


