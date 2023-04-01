package com.edu.ucne.uniqueapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.edu.ucne.uniqueapp.data.util.Screen
import com.edu.ucne.uniqueapp.ui.citas.CitasListScreen
import com.edu.ucne.uniqueapp.ui.citas.CitasScreen

@Composable
fun NavigationManager(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {

      /*  composable(Screen.HomeScreen.route) {
            HomeScreen(navController)
        }*/
        /*composable(route = Screen.HomeScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType})
            ){capturar ->
            val citaId = capturar.arguments?.getInt("id")?:0
            HomeScreen(CitasScreen(citaId = citaId) {
                navController.navigate(Screen.CitasScreen.route)
            }*/


      /*  composable(route = Screen.CitasScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id"){type = NavType.IntType})
        ){capturar ->
            val spaId = capturar.arguments?.getInt("id")?:0
            SpaScreen(spaId = spaId){
                navController.navigate(Screen.SpaList.route)
            }
        }
        composable( route = Screen.SalonScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType }))
        { navEntry ->
            val salonId = navEntry.arguments?.getInt("id") ?:0
            SalonScreen(salonId = salonId){
                navController.navigate(Screen.SalonList.route)
            }
        }

        composable( route = Screen.NailsScreen.route + "/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType }))
        { navEntry ->
            val nailsId = navEntry.arguments?.getInt("id") ?:0
            NailsScreen(nailsId){
                navController.navigate(Screen.NailsList.route)
            }
        }*/
/*
        composable(
            route = Screen.CitaList.route
        ) {
            CitasListScreen(onNewCita = { }) { id ->
                navController.navigate(Screen.CitasScreen.route + "/${id}")
            }
        }*/
        /*composable(
            route = Screen.SalonList.route
        ) {
            SalonListScreen(onNewSalon = { }) { id ->
                navController.navigate(Screen.SalonScreen.route + "/${id}")
            }
        }

        composable(
            route = Screen.NailsList.route
        ) {
            NailsListScreen(onNewNail = { }) { id ->
                navController.navigate(Screen.NailsScreen.route + "/${id}")
            }
        }
        */

    }

}
