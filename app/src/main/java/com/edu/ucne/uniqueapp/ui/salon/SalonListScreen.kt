package com.edu.ucne.uniqueapp.ui.salon

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
import com.edu.ucne.uniqueapp.data.remote.dto.SalonDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalonListScreen(
    onNewSalon: () -> Unit,
    viewModel: SalonViewModel = hiltViewModel(),
    onSalonClick: (Int) -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista De Salon",
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
            SalonListBody(uiState.salon) {
                onSalonClick(it)
            }
        }
    }
}

@Composable
fun SalonListBody(salonList: List<SalonDto>, onSalonClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn{
            items(salonList){salon ->
                SalonRow(salon){
                    onSalonClick(it)
                }
            }
        }
    }
}

@Composable
fun SalonRow(salon: SalonDto, onSalonClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onSalonClick(salon.salonId) })
        ) {
            Row() {

                Text(
                    text = salon.salonServicio,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = salon.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = salon.horario,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}

