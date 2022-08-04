package omarjarid.studioghibliapp

import android.os.Bundle
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
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import omarjarid.studioghibliapp.presentation.viewmodels.FilmViewModel
import omarjarid.studioghibliapp.ui.composables.StudioGhibliAppCompose
import omarjarid.studioghibliapp.ui.theme.StudioGhibliDemoAppTheme

/*
    Una volta settata la classe Application, Hilt può fornire le dipendenze alle classi annotate con
    @AndroidEntryPoint.

    In questo caso specifico, dato che uso Jetpack Compose con Activity singola, l'unica classe con
    quest'annotazione è quest'Activity (punto di ingresso dell'app, per convenzione chiamato
    MainActivity).

    Tipicamente, con la vecchia view, le classi che possono avere quest'annotazione sono Activity o
    Fragment (**), ma se annoto un Fragment con @AndroidEntryPoint devo poi annotare qualsiasi
    Activity che lo usa.

    (**)
    Activity = classe che definisce QUALE LAYOUT USARE e COSA L'APP FA per rispondere
    all'interazione con l'utente. Per esempio, se il layout associato all'Activity include un
    bottone, devo scrivere del codice nell'Activity per intercettare la pressione del bottone e
    definire che cosa l'app deve fare quando esso viene premuto.

    Fragment = componente modulare che può essere riusato da Activity diverse.
    Si usa per controllare parte di una schermata. Anch'esso ha un layout associato, per cui se il
    suo codice contiene tutto ciò che serve per controllare il layout associato, aumentano le chance
    di poterlo riutilizzare in altri punti dell'app. In questo specifico caso qui non ci saranno
    Fragment, ho dato la definizione per completezza.
*/

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    // ViewModels dichiarati come delegate.
    private val viewModel: FilmViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Funzioni load*().
        viewModel.loadFilms()

        // Necessario per avere l'immagine dietro la status bar.
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            // Recupero gli StateFlow con collectAsState().
            val films = viewModel.films.collectAsState()

            StudioGhibliDemoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    // Il resto avviene in questa funzione.
                    StudioGhibliAppCompose(
                        list = films.value,
                        textState = remember { mutableStateOf("") },
                        viewModel = viewModel,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}