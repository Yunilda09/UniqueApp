package com.edu.ucne.uniqueapp.ui.nails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.edu.ucne.uniqueapp.data.remote.dto.NailsDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NailsListScreen(
    onNewNail: () -> Unit, viewModel: NailsViewModel = hiltViewModel(),
    onNailClick: (Int) -> Unit
) {
    //

    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista De Uñas",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineLarge
                    )
                }
            )
        },
    ) {
        val uiState by viewModel.uiState.collectAsState()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            NailListBody(uiState.nails) {
                onNailClick(it)
            }
        }
    }
}

@Composable
fun NailListBody(nailsList: List<NailsDto>, onNailClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn{
            items(nailsList){nail ->
                NailRow(nail){
                    onNailClick(it)
                }
            }
        }
    }
}


@Composable
fun NailRow(nail: NailsDto, onNailClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onNailClick(nail.nailsId) })
        ) {
            Row() {

                Text(
                    text = nail.nailsServicio,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = nail.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = nail.horario,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}


