package omarjarid.example.data

import omarjarid.example.domain.model.*

interface NetworkDatasource {
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilm(id: String): Film
}