package omarjarid.studioghibliapp

import androidx.navigation.NavHostController

/*** FUNZIONE DI NAVIGAZIONE ***/
// L'ho messa come internal perché DEVE poter essere usata solo nel modulo app.
internal fun navigateTo(route: String, navController: NavHostController) {
    navController.navigate(route)
}
