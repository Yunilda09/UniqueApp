package com.edu.ucne.uniqueapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.edu.ucne.uniqueapp.R
import com.edu.ucne.uniqueapp.data.remote.dto.CitaDto
import com.edu.ucne.uniqueapp.ui.citas.CitasViewModel
import com.edu.ucne.uniqueapp.ui.componentes.CitasRow
import com.edu.ucne.uniqueapp.ui.inicio.InicioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioScreen(navController: NavHostController,
    viewModel: InicioViewModel = hiltViewModel(),
                 onSaveClick: () -> Unit,
        onCitaClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFFFF3F5))
    ) {
        Image(
            painter = painterResource(id = R.drawable.salon_and_spa), contentDescription = "header",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = "Date un tiempo para ti",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFFC45559)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Inicio(viewModel = viewModel, onSaveClick){
          onCitaClick(it)
        }
    }
}

@Composable
fun Inicio(viewModel: InicioViewModel,
            onSaveClick:() -> Unit,
           onCitaClick: (Int) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()
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
            onClick = { },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC3ECDE),
                contentColor = Color(0xFFC45559)
            )
        ) {
            Text(text = "Ver mis citas")
        }
        val uiState =  viewModel.uiState.collectAsState()
        CitasProximas(listCitas = uiState.value.citasProximas,
            onCitaSwipe = {viewModel.cancelarCita(it)},
            onCitaClick = {onCitaClick(it)})
    }
}

@Composable
fun CitasProximas(listCitas: List<CitaDto>, onCitaSwipe: (Int) -> Unit, onCitaClick: (Int) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(
            text = "Proximas Citas",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFFC45559)
        )
        listCitas.forEach{
            CitasRow(onCitaSwipe = onCitaSwipe, citaDto = it , onCitaClick = onCitaClick )
        }

    }
}


