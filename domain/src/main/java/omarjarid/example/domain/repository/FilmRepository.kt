package omarjarid.example.domain.repository

import omarjarid.example.domain.model.Film

interface FilmRepository {
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilm(id: String): Film
}