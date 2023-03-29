package com.edu.ucne.uniqueapp.data.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face3
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Handshake
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Start : Screen("start","Inicio", Icons.TwoTone.Favorite)
    object SpaScreen: Screen("registro_Spa", "Spa", Icons.TwoTone.Spa)
    object SalonScreen: Screen("registro_Salon", "Salon", Icons.TwoTone.Face3)
    object NailsScreen: Screen("registro_Nails", "Nails", Icons.TwoTone.Handshake)
    object SpaList: Screen("consulta_Spa", "Consulta Spa", Icons.TwoTone.Spa)
    object SalonList: Screen("consulta_Salon", "Consulta Salon", Icons.TwoTone.Face3)
    object NailsList: Screen("consulta_Nails", "Consulta Nails", Icons.TwoTone.Handshake)

}