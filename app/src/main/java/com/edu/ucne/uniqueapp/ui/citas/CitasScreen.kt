package com.edu.ucne.uniqueapp.ui.citas

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.ui.componentes.DatePickerDialog
import com.edu.ucne.uniqueapp.ui.componentes.TimePickerDialog

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitasScreen(
    citaId: Int,
    viewModel: CitasViewModel = hiltViewModel(),
    onsaveClick:() -> Unit,
){ Column(modifier = Modifier
    .fillMaxHeight()
    .background(Color(0xFFFFF3F5))) {


    remember {
        viewModel.setCita(citaId)
        0
    }
    CitasBody(viewModel = viewModel) {
        onsaveClick()
    }
}
}

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CitasBody(
    viewModel: CitasViewModel,
    onSaveClick: () -> Unit
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
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color(0xFFC45559)
                    )
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(8.dp),

                content = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save",
                    tint = Color(0xFFC45559)) },
                onClick = {
                    viewModel.guardar()
                    onSaveClick()
                }, containerColor = Color(0xFFFEDEE2)
            )
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(0xFFFFF3F5))
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
                label = { Text("Servicios") }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)

            ) {
                val servicios by viewModel.uiStateServicios.collectAsState()
               servicios.servicios.forEach { opcion ->
                    DropdownMenuItem(
                        text = {
                            Text(text = opcion.descripcion, textAlign = TextAlign.Center)
                        },
                        onClick = {
                            expanded = false
                            viewModel.setServicio(opcion.servicioId?: 0, opcion.descripcion)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp)
                    )
                }
            }

            var showDatePicker by remember {
                mutableStateOf(false)
            }
            if(showDatePicker)
                DatePickerDialog(
                    label = "Seleccione la fecha" ,
                    onSelectDate ={ viewModel.fecha = it })
                    {
                        showDatePicker = false
                }

            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { showDatePicker = true },
                value = viewModel.fecha,
                onValueChange = { viewModel.fecha = it },
                enabled = false,
                readOnly = true,
                label = { Text("Fecha") }
            )

            var showTimePicker by remember {
                mutableStateOf(false)
            }
            if (showTimePicker)
                TimePickerDialog(
                    label = "Seleccione la hora",
                    onSelectTime = {viewModel.hora = it} ) {
                    showTimePicker = false
                }
            OutlinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { showTimePicker = true },
                value = viewModel.hora,
                onValueChange = {viewModel.hora = it},
                enabled = false,
                readOnly = true,
                label = { Text("Hora") })
        }
    }
}


