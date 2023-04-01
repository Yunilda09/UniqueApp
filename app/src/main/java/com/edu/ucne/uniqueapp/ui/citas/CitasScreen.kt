package com.edu.ucne.uniqueapp.ui.citas

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasScreen(
    citaId: Int,
    viewModel: CitasViewModel = hiltViewModel(),
    onsaveClick:() -> Unit
){
    remember {
        viewModel.setCita(citaId)
        0
    }
    CitasBody(viewModel = viewModel){
        onsaveClick()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CitasBody(
    viewModel: CitasViewModel,
    onsaveClick: () -> Unit
){
   var expanded by remember {
        mutableStateOf(false)
    }


    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Crea tu cita",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.nombre,
                onValueChange = { viewModel.nombre = it },
                label = { Text("Nombres") }
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.apellido,
                onValueChange = { viewModel.apellido = it },
                label = { Text("Apellidos") }
            )
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { expanded = true },
                value = viewModel.servicios,
                enabled = false, readOnly = true,
                onValueChange = { viewModel.servicios = it },
                label = { Text("Estatus") }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)

            ) {
                viewModel.opcionesServicios.forEach { opcion ->
                    DropdownMenuItem(
                        text = {
                            Text(text = opcion, textAlign = TextAlign.Center)
                        },
                        onClick = {
                            expanded = false
                            viewModel.servicios = opcion
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.fecha,
                onValueChange = { viewModel.fecha = it },
                label = { Text("Fecha") }
            )

        }
    }
}


