package com.edu.ucne.uniqueapp.ui

import android.os.Build
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationManager(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {
        composable(Screen.Inicio.route) {
            InicioScreen(navController = navController, onSaveClick = {
                navController.navigate(Screen.CitaScreen.route + "/0")}){
                navController.navigate(Screen.CitaScreen.route + "/$it")
            }
        }
          composable( route = Screen.CitaScreen.route + "/{id}",
                arguments = listOf(navArgument("id") { type = NavType.IntType }))
            { navEntry ->
                val citasId = navEntry.arguments?.getInt("id") ?:0
                CitasScreen(citasId){
                    navController.navigateUp()
                }
            }


        }

    }

