package com.edu.ucne.uniqueapp.ui.citas


import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.ui.componentes.*

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CitasScreen(
    citaId: Int,
    viewModel: CitasViewModel = hiltViewModel(),
    onsaveClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color(0xFFFFF3F5))
    ) {

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
) {
    var expanded by remember {
        mutableStateOf(false)
    }
    var allVerified by remember { mutableStateOf(false) }

    val intNombreSource = remember { MutableInteractionSource() }
    val nombreFocus by intNombreSource.collectIsFocusedAsState()
    var nombreGotFocus by remember { mutableStateOf(false) }
    if (nombreFocus) nombreGotFocus = true

    val intApellidoSource = remember { MutableInteractionSource() }
    val apellidoFocus by intApellidoSource.collectIsFocusedAsState()
    var apellidoGotFocus by remember { mutableStateOf(false) }
    if (apellidoFocus) apellidoGotFocus = true


    var gotFocusServicio by remember { mutableStateOf(false) }
    var gotFocusFecha by remember { mutableStateOf(false) }
    var gotFocusHora by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxWidth(),
        topBar = {
            RegistroTopBar(onSaveClick)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .padding(8.dp),

                content = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
                onClick = {
                    allVerified = true
                    if (viewModel.validar()) {
                        viewModel.guardar()
                        onSaveClick()
                    }
                }
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.primary)
        ) {
            val uiState by viewModel.uiState.collectAsState()
            if (uiState.isLoading) {
                LoadingDialog()
            }

            val focusManager = LocalFocusManager.current

            /* NOMBRE */
            OutLinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.nombre,
                interactionSource = intNombreSource,
                singleLine = true, maxLines = 1,
                isError = viewModel.nombreError.isNotEmpty() && (nombreGotFocus || allVerified),
                errorMsg = viewModel.nombreError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                onValueChange = { /*viewModel.asignarNombre*/(it) },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Person, contentDescription = "Nombre")
                },
                label = { Text("Nombres") }
            )

            /* APELLIDO */
            OutLinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.apellido,
                interactionSource = intApellidoSource,
                singleLine = true, maxLines = 1,
                isError = viewModel.apellidoError.isNotEmpty() && (apellidoGotFocus || allVerified),
                errorMsg = viewModel.apellidoError,
                keyboardOptions = KeyboardOptions.Default.copy(
                    capitalization = KeyboardCapitalization.Words,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { expanded = true }),
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.PersonPin, contentDescription = null)
                },
                onValueChange = { viewModel.asignarApellido(it) },
                label = { Text("Apellidos") }
            )

            /*SERVICIOS*/
            OutLinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        expanded = true
                        gotFocusServicio = true
                    },
                value = viewModel.servicios,
                isError = viewModel.servicioError.isNotEmpty() && (gotFocusServicio || allVerified),
                errorMsg = viewModel.servicioError,
                enabled = false, readOnly = true,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.ExpandMore, contentDescription = null)
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Spa, contentDescription = null)
                },
                onValueChange = { viewModel.asignarServicio(it) },
                label = { Text("Servicios") }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
            {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()

                ) {
                    val servicios by viewModel.uiStateServicios.collectAsState()
                    servicios.servicios.forEach { opcion ->
                        DropdownMenuItem(
                            text = {
                                Text(text = opcion.descripcion, textAlign = TextAlign.Center)
                            },
                            onClick = {
                                expanded = false
                                viewModel.asignarServicio(
                                    opcion.servicioId ?: 0,
                                    opcion.descripcion
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp)
                        )
                    }
                }
            }

            var showDatePicker by remember {
                mutableStateOf(false)
            }
            if (showDatePicker)
                DatePickerDialog(
                    label = "Seleccione la fecha",
                    onSelectDate = { viewModel.asignarFecha(it) })
                {
                    showDatePicker = false
                }

            OutLinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        gotFocusFecha = true
                        showDatePicker = true
                    },
                value = viewModel.fecha,
                onValueChange = { viewModel.asignarFecha(it) },
                enabled = false,
                isError = viewModel.fechaError.isNotEmpty() && (gotFocusFecha || allVerified),
                errorMsg = viewModel.fechaError,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.Event, contentDescription = null)
                },

                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.DateRange, contentDescription = null)
                },
                readOnly = true,
                label = { Text("Fecha") }
            )

            var showTimePicker by remember {
                mutableStateOf(false)
            }
            if (showTimePicker)
                TimePickerDialog(
                    label = "Seleccione la hora",
                    onSelectTime = { viewModel.asignarHora(it) }) {
                    showTimePicker = false
                }
            OutLinedTextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable {
                        gotFocusHora = true
                        showTimePicker = true
                    },
                value = viewModel.hora,
                isError = viewModel.horaError.isNotEmpty() && (gotFocusHora || allVerified),
                errorMsg = viewModel.horaError,
                onValueChange = { viewModel.asignarHora(it) },
                enabled = false,
                trailingIcon = {
                    Icon(imageVector = Icons.Outlined.AlarmAdd, contentDescription = null)
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Alarm, contentDescription = null)
                },
                readOnly = true,
                label = { Text("Hora") })

            if (viewModel.estadoId == 1) {
                var showConfirmation by remember { mutableStateOf(false) }
                if (showConfirmation) {
                    ConfirmationDialog(onDismiss = { showConfirmation = false }) {
                        viewModel.cancelarCita(viewModel.citaId)
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            showConfirmation = true
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC3ECDE),
                            contentColor = Color(0xFF51BC98)
                        )
                    ) {
                        Text("Cancelar Cita")
                    }
                }
            }

        }
    }
}


