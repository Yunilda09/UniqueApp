package com.edu.ucne.uniqueapp.ui.salon

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalonScreen(
    salonId: Int ,
    viewModel: SalonViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    remember {
        viewModel.setSalon(salonId)
        0
    }
    Column(Modifier.fillMaxWidth()) {

        SalonBody(viewModel = viewModel){
            onSaveClick()
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SalonBody(
    viewModel: SalonViewModel,
    onSaveClick: () -> Unit
) {
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
                        "Registro De Salon",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(8.dp),

                //text = { Text("Guardar") },
                content = {
                    Icon(
                        imageVector = Icons.Filled.Save,
                        contentDescription = "Save"
                    )
                },
                onClick = {
                    //viewModel.putSalon()
                   // onSaveClick()
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
        ) {

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)

            ) {
                viewModel.opcionessalonServicio.forEach { opcion ->
                    DropdownMenuItem(
                        text = {
                            Text(text = opcion, textAlign = TextAlign.Center)
                        },
                        onClick = {
                            expanded = false
                            viewModel.salonServicio = opcion
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
                value = viewModel.horario,
                onValueChange = { viewModel.horario = it },
                label = { Text("Horario") }
            )
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
