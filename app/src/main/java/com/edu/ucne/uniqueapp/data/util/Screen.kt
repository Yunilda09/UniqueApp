package com.edu.ucne.uniqueapp.data.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Face3
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material.icons.twotone.Handshake
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Inicio : Screen("start")
    object CitaScreen : Screen("registro_Cita")
    object CitaListScreen : Screen("consulta_Cita")
    object ServicioListScreen : Screen("consulta_Servicio")
    object SignIn : Screen("signIn")
    object SignOut : Screen("signOut")
}