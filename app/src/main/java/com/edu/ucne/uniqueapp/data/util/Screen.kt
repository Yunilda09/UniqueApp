package com.edu.ucne.uniqueapp.data.util

sealed class Screen(val route: String) {
    object NailsListScreen: Screen("Consulta")
    object NailsScreen: Screen("Registro")

    object SalonListScreen: Screen("Consulta")
    object SalonScreen: Screen("Registro")

    object SpaListScreen: Screen("Consulta")
    object SpaScreen: Screen("Registro")
}