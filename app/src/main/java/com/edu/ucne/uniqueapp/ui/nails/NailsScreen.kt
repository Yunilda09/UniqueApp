@file:Suppress("PreviewAnnotationInFunctionWithParameters")

package com.edu.ucne.uniqueapp.ui.nails

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NailsScreen(
    nailId: Int,
    viewModel: NailsViewModel = hiltViewModel(),
    onSaveClick: () -> Unit
) {
    remember {
        viewModel.setNail(nailId)
        0
    }
    Column(Modifier.fillMaxWidth()) {

        NailBody(viewModel = viewModel){
            onSaveClick()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true, showSystemUi = true)
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NailBody(
    viewModel: NailsViewModel,
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
                            "Registro De Uñas",
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
                        //viewModel.putNail()
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
                    viewModel.opcionesnailsServicio.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(text = opcion, textAlign = TextAlign.Center)
                            },
                            onClick = {
                                expanded = false
                                viewModel.nailsServicio = opcion
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



