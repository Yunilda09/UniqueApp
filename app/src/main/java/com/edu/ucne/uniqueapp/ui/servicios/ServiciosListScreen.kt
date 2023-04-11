package com.edu.ucne.uniqueapp.ui.servicios

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.TaskAlt
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.data.remote.dto.Cita
import com.edu.ucne.uniqueapp.data.remote.dto.ServiciosDto
import com.edu.ucne.uniqueapp.ui.citas.CitasViewModel
import com.edu.ucne.uniqueapp.ui.componentes.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiciosListScreen(
    servicioId: Int = 1,
    viewModel: ServicioViewModel = hiltViewModel(),
    navigateUp: () -> Unit,
    onServicioClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            ListTopBar(label = "Lista de Servicios", onBackPress = navigateUp)
        },

        ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            ServiciosListBody(uiState.servicios) {
                onServicioClick(it)
            }
        }
    }
}

@Composable
fun ServiciosListBody(servicioList: List<ServiciosDto>, onServicioClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()
        .padding(top = 64.dp)
        .background(Color(0xFFFFEEED))) {
        LazyColumn {
            items(servicioList) { servicio ->
                ServicioRow(servicio) {
                    onServicioClick(it)
                }
            }
        }
    }
}

