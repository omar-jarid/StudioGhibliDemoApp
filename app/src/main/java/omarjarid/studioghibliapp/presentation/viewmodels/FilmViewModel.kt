package omarjarid.studioghibliapp.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import omarjarid.example.domain.model.Film
import omarjarid.example.domain.repository.FilmRepository
import javax.inject.Inject

/*
    Qui c'è una parte cruciale per l'app.
    Sto utilizzando il design pattern MVVM (Model-View-ViewModel).
    Esso si compone di 3 parti:

    - il Model (cioè la logica di business, che contiene i dati a cui desideriamo accedere)
    - la View (ovvero la parte grafica dell'app che intercetta l'interazione con l'utente)
    - il ViewModel (che fa da ponte tra View e Model).

    COSA SUCCEDE

    - la View intercetta l'interazione dell'utente con la UI (es. tap di un bottone) e manda
      l'evento intercettato al ViewModel
    - il ViewModel effettua le chiamate di rete per reperire i dati dal Model
    - siccome la chiamata è asincrona, il risultato viene salvato in uno StateFlow (in questo caso
      specifico; oppure in un LiveData nella vecchia view) che viene osservato dalla View
    - una volta visti i dati reperiti, la View aggiorna la UI con l'output opportuno

    **********************************************************************************************

    L'annotazione @HiltViewModel dice a Hilt come fornire l'istanza del ViewModel.
    Inoltre inietto il Repository che avevo definito in data (collegato alla sua implementazione
    tramite RepositoryModule).
*/
@HiltViewModel
class FilmViewModel @Inject constructor(private val repository: FilmRepository) : ViewModel() {
    // MutableStateFlow che inizializzo come vuoto e che gestisco all'interno del ViewModel.
    private val _films = MutableStateFlow(emptyList<Film>())

    // StateFlow pubblico e non mutabile che vado a esporre nella View.
    val films: StateFlow<List<Film>> = _films

    // Lista che gestisco all'interno del ViewModel.
    private var lista: List<Film> = emptyList()

    // Il Job rappresenta un compito in background. Lo dichiaro ora come nullable e lo setto a null.
    private var job: Job? = null

    // Chiamata di rete asincrona.
    fun loadFilms() {
        /*
            Sto utilizzando le coroutine, che sono un meccanismo di Kotlin che semplifica la
            programmazione asincrona.

            Le due istruzioni avvengono in maniera non bloccante nello scope del ViewModel.
        */
        viewModelScope.launch {
            /*
                Farei direttamente _films.value = repository.getAllFilms(), ma poi devo filtrare la
                lista con la funzione di ricerca senza fare chiamate di rete.
            */
            lista = repository.getAllFilms()
            _films.value = lista
        }
    }

    // Funzione di ricerca.
    fun search(text: String) {
        // Se il job non è nullo lo interrompo, altrimenti non faccio nulla.
        job?.cancel()

        /*
            Salvo nel job il filtraggio della lista in background, a seconda del testo che passo.
            Naturalmente il tutto deve avvenire in maniera asincrona altrimenti la View va in freeze
            e non posso digitare nulla.
        */
        job = viewModelScope.launch {
            // Metto un delay di 300 millisecondi altrimenti mi è impossibile digitare.
            delay(300)
            _films.value = lista.filter { it.title.contains(text, ignoreCase = true) }
        }
    }
}