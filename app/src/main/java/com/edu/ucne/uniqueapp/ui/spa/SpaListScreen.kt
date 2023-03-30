package com.edu.ucne.uniqueapp.ui.spa

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
import com.edu.ucne.uniqueapp.data.remote.dto.SpaDto


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpaListScreen(
    onNewSpa: () -> Unit,
    viewModel: SpaViewModel = hiltViewModel(),
    onSpaClick: (Int) -> Unit
){
    Scaffold(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Lista De Spa",
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
            SpaListBody(uiState.spa) {
                onSpaClick(it)
            }
        }
    }
}

@Composable
fun SpaListBody(spaList: List<SpaDto>, onSpaClick: (Int) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn{
            items(spaList){spa ->
                SpaRow(spa){
                    onSpaClick(it)
                }
            }
        }
    }
}

@Composable
fun SpaRow(spa: SpaDto, onSpaClick: (Int) -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = { onSpaClick(spa.spaId) })
        ) {
            Row() {

                Text(
                    text = spa.spaServicio,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )
               /* Text(
                    text = spa.fecha.substring(0, 10),
                    style = MaterialTheme.typography.titleSmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier.weight(3f)
                )
                Text(
                    text = spa.horario,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.weight(3f)
                )*/
            }
        }
        Divider(Modifier.fillMaxWidth())
    }
}
