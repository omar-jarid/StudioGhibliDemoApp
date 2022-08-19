package omarjarid.studioghibliapp

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import omarjarid.studioghibliapp.presentation.viewmodels.FilmViewModel
import omarjarid.studioghibliapp.ui.composables.StudioGhibliAppCompose
import omarjarid.studioghibliapp.ui.theme.StudioGhibliDemoAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadFilms()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        setContent {
            val films = viewModel.films.collectAsState()
            val list = films.value

            StudioGhibliDemoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) { StudioGhibliAppCompose(list) }
            }
        }
    }
}