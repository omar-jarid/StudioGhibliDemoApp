package omarjarid.studioghibliapp

import androidx.navigation.NavHostController

/*** FUNZIONE DI NAVIGAZIONE ***/
internal fun navigateTo(route: String, navController: NavHostController) {
    navController.navigate(route)
}
