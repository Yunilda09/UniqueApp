package com.edu.ucne.uniqueapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.CalendarMonth
import androidx.compose.material.icons.twotone.Home
import androidx.compose.material.icons.twotone.LibraryAdd
import androidx.compose.material.icons.twotone.Spa
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.edu.ucne.uniqueapp.data.util.Screen
import com.edu.ucne.uniqueapp.ui.citas.CitasListScreen
import com.edu.ucne.uniqueapp.ui.citas.CitasScreen
import com.edu.ucne.uniqueapp.ui.componentes.NavItem
import com.edu.ucne.uniqueapp.ui.login.LoginScreen
import com.edu.ucne.uniqueapp.ui.servicios.ServiciosListScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationManager(navController: NavHostController) {

    var idUsuarioLogin by remember {
        mutableStateOf(0)
    }
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {

        val listaNavegacion = listOf(
            NavItem({ navController.navigate(Screen.Inicio.route) }, Icons.TwoTone.Home, "Home"),
            NavItem(
                { navController.navigate(Screen.CitaScreen.route + "/0") },
                Icons.TwoTone.LibraryAdd,
                "Nueva Cita"
            ),
            NavItem(
                { navController.navigate(Screen.CitaListScreen.route) },
                Icons.TwoTone.CalendarMonth,
                "Mis Citas"
            ),
            NavItem(
                { navController.navigate(Screen.ServicioListScreen.route) },
                Icons.TwoTone.Spa,
                "Servicios"
            ),
        )

        composable(Screen.Inicio.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val clienteId = it.arguments?.getInt("id") ?: 0
            InicioScreen(
                clienteId,
                listaNavegacion,
                onVerMisCitasClick = { navController.navigate(Screen.CitaListScreen.route) },
                onSaveClick = { navController.navigate(Screen.CitaScreen.route + "/0") }) {
                navController.navigate(Screen.CitaScreen.route + "/$it")
            }
        }
        composable(
            route = Screen.CitaScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        )
        { navEntry ->
            val citasId = navEntry.arguments?.getInt("id") ?: 0
            CitasScreen(citaId = citasId, clienteId = idUsuarioLogin) {
                navController.navigateUp()
            }
        }

        composable(Screen.CitaListScreen.route) {
            CitasListScreen(
                idUsuarioLogin,
                onNewCita = { navController.navigate(Screen.CitaScreen.route + "/0") },
                navigateUp = { navController.navigateUp() }) {
                navController.navigate(Screen.CitaScreen.route + "/$it")
            }
        }
        composable(Screen.ServicioListScreen.route) {
            ServiciosListScreen(
                navigateUp = { navController.navigateUp() }) {
                navController.navigate(Screen.Inicio.route + "/$it")
            }

        }
        composable(Screen.LoginScreen.route){
            LoginScreen(login = {
                idUsuarioLogin = it
                navController.navigate(Screen.Inicio.route + "/$it")})
        }

    }
}

