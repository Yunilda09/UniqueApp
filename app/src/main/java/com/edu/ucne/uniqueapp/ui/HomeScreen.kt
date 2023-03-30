package com.edu.ucne.uniqueapp.ui

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.edu.ucne.uniqueapp.R
import com.edu.ucne.uniqueapp.data.util.Screen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Column(modifier = Modifier.fillMaxHeight()) {
        Image(painter = painterResource(id = R.drawable.salon_and_spa), contentDescription = "header",
            modifier = Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Date un tiempo para ti",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Color(0xFFC45559))
        Spacer(modifier = Modifier.padding(4.dp))
        Inicio()
        Spacer(modifier = Modifier.padding(8.dp))
        CitasProximas()
    }
}

@Composable
fun Inicio(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFEDEE2),
                contentColor = Color(0xFFC45559))
            ) {
            Text(text = "Crear cita")
        }
        Button(onClick = { /*TODO*/ },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC3ECDE),
                contentColor = Color(0xFFC45559))
        ){
            Text(text = "Ver mis citas")
        }
    }
}
@Composable
fun CitasProximas(){
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Proximas Citas",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                color = Color(0xFFC45559))
    }
}


