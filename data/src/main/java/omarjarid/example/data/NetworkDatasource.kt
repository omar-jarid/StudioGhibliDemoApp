package omarjarid.example.data

import omarjarid.example.domain.model.*

/*
    Quest'interfaccia crea uno strato di astrazione che ha lo scopo di fare da ponte per fornire
    l'accesso ai dati dal modulo app (che è quello più esterno e dipende da questo), in cui andrò
    a definire la sua implementazione concreta.
*/
interface NetworkDatasource {
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilm(id: String): Film
}