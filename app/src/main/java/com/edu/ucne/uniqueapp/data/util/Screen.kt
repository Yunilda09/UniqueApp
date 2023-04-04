package com.edu.ucne.uniqueapp.data.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face3
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Handshake
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Inicio : Screen("start","Inicio", Icons.TwoTone.Favorite)
    object CitaScreen: Screen("registro_Cita", "Cita", Icons.TwoTone.Spa)

}