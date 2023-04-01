package com.edu.ucne.uniqueapp.data.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face3
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Handshake
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object HomeScreen : Screen("start","Inicio", Icons.TwoTone.Favorite)
    object CitasScreen: Screen("registro_Cita", "Cita", Icons.TwoTone.Spa)
    object SalonScreen: Screen("registro_Salon", "Salon", Icons.TwoTone.Face3)
    object NailsScreen: Screen("registro_Nails", "Nails", Icons.TwoTone.Handshake)
    object CitaList: Screen("consulta_Cita", "Consulta Cita", Icons.TwoTone.Spa)
    object SalonList: Screen("consulta_Salon", "Consulta Salon", Icons.TwoTone.Face3)
    object NailsList: Screen("consulta_Nails", "Consulta Nails", Icons.TwoTone.Handshake)

}