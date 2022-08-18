package omarjarid.studioghibliapp.ui.composables

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import omarjarid.example.domain.model.Film
import omarjarid.studioghibliapp.presentation.viewmodels.FilmViewModel

// Ora StudioGhibliNavHost è stateless.
@Composable
fun StudioGhibliNavHost(
    listFilms: List<Film>,
    viewModel: FilmViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "films") {
        composable(route = "films") {
            // Solo FilmBodyContent è stateful!
            FilmBodyContent(lista = listFilms, viewModel = viewModel, navController = navController)
        }

        composable(
            route = "films/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("id").let { id ->
                FilmDetail(film = listFilms.first { it.id == id }, navController = navController)
            }
        }
    }
}

// Ora StudioGhibliAppCompose è stateless.
@Composable
fun StudioGhibliAppCompose(viewModel: FilmViewModel) {
    val films = viewModel.films.collectAsState()
    val list = films.value

    Scaffold { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            CircularProgressBar(isDisplayed = list.isEmpty())
            StudioGhibliNavHost(listFilms = list, viewModel = viewModel)
        }
    }
}