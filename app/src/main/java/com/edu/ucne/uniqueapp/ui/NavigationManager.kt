package com.edu.ucne.uniqueapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.edu.ucne.uniqueapp.data.util.Screen
import com.edu.ucne.uniqueapp.ui.nails.NailsListScreen
import com.edu.ucne.uniqueapp.ui.nails.NailsScreen
import com.edu.ucne.uniqueapp.ui.salon.SalonScreen

@Composable
fun NavigationManageger(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.NailsListScreen.route
    ) {
        composable(
            route = Screen.NailsListScreen.route
        ) {
            NailsListScreen(onNewNail = { }) { id ->
                navController.navigate(Screen.NailsScreen.route + "/${id}")
            }
        }
        // Nails Screen
        composable(
            route = Screen.NailsScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.IntType })
        ) { capturar ->
            val nailId = capturar.arguments?.getInt("id") ?: 0
            NailsScreen(
                nailId = nailId
            ) {
                navController.navigate(Screen.NailsListScreen.route)
            }
            //Salon Screen
            /* composable(
                 route = Screen.SalonScreen.route + "/{id}",
                 arguments = listOf(
                     navArgument("id") { type = NavType.IntType })
             ) { capturar ->
                 val salonId = capturar.arguments?.getInt("id") ?: 0

                 SalonScreen(salonId = salonId) {
                     navController.navigate(Screen.SalonListScreen.route)
                 }
             }*/
        }
    }

}
