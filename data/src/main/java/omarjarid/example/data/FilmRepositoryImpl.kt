package omarjarid.example.data

import omarjarid.example.domain.model.Film
import omarjarid.example.domain.repository.FilmRepository
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkDatasource
) : FilmRepository {
    override suspend fun getAllFilms(): List<Film> = networkDatasource.getAllFilms()
    override suspend fun getFilm(id: String): Film = networkDatasource.getFilm(id)
}