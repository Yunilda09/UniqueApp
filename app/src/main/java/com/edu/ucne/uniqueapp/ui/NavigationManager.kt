package com.edu.ucne.uniqueapp.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.edu.ucne.uniqueapp.data.util.Screen
import com.edu.ucne.uniqueapp.ui.nails.NailsListScreen
import com.edu.ucne.uniqueapp.ui.nails.NailsScreen
import com.edu.ucne.uniqueapp.ui.salon.SalonListScreen
import com.edu.ucne.uniqueapp.ui.salon.SalonScreen
import com.edu.ucne.uniqueapp.ui.spa.SpaListScreen
import com.edu.ucne.uniqueapp.ui.spa.SpaScreen

@Composable
fun NavigationManager(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Start.route
    ) {
        composable(Screen.Start.route) {
            HomeScreen(navController)
        }

       /* composable(route = Screen.SpaScreen.route + "/{id}",
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

        composable(
            route = Screen.SpaList.route
        ) {
            SpaListScreen(onNewSpa = { }) { id ->
                navController.navigate(Screen.SpaScreen.route + "/${id}")
            }
        }
        composable(
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

    }

}
