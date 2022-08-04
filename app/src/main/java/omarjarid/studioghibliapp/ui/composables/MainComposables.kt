package omarjarid.studioghibliapp.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.Routes
import omarjarid.studioghibliapp.presentation.viewmodels.FilmViewModel

// Punto di navigazione.
@Composable
fun StudioGhibliNavHost(
    listFilms: List<Film>,
    textState: MutableState<String>,
    filmViewModel: FilmViewModel,
    navController: NavHostController
) {
    // QUI vado a costruire la navigation del mio programma.
    NavHost(navController = navController, startDestination = Routes.SPLASH) {
        composable(Routes.SPLASH) { SplashScreen(navController = navController) }
        composable(Routes.FILMS) {
            FilmBodyContent(
                lista = listFilms,
                textState = textState,
                viewModel = filmViewModel,
                navController = navController
            )
        }

        composable(
            route = "${Routes.FILMS}/{id}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType }
            )
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("id")?.let { id ->
                FilmDetail(
                    film = listFilms.first { it.id == id },
                    navController = navController
                )
            }
        }
    }
}

// Composable principale, chiama il NavHost.
@Composable
fun StudioGhibliAppCompose(
    listFilms: List<Film>,
    textState: MutableState<String>,
    filmViewModel: FilmViewModel,
    navController: NavHostController
) {
    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            // Qui potrebbe volerci una progress bar.
            StudioGhibliNavHost(
                listFilms = listFilms,
                textState = textState,
                filmViewModel = filmViewModel,
                navController = navController
            )
        }
    }
}