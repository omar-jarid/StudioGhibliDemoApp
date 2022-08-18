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

@HiltViewModel
class FilmViewModel @Inject constructor(private val repository: FilmRepository) : ViewModel() {
    private val _films = MutableStateFlow(emptyList<Film>())
    val films: StateFlow<List<Film>> = _films
    private var lista: List<Film> = emptyList()
    private var job: Job? = null

    fun loadFilms() {
        viewModelScope.launch {
            lista = repository.getAllFilms()
            _films.value = lista
        }
    }

    // Metodo di ricerca.
    fun search(text: String) {
        job?.cancel()
        job = viewModelScope.launch {
            _films.value = lista.filter { it.title.contains(text, ignoreCase = true) }
        }
    }
}