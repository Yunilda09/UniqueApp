package com.edu.ucne.uniqueapp.ui.citas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CitasScreen(){

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
}

