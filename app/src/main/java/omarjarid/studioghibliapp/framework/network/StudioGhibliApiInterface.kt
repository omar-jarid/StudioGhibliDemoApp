package omarjarid.studioghibliapp.framework.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

/*
    Retrofit permette di esprimere le chiamate HTTP a un'API come metodi di un'interfaccia Java
    (o Kotlin, che tanto la compilazione produce lo stesso bytecode).
*/
interface StudioGhibliApiInterface {
    @GET("films")
    suspend fun getAllFilms(): Response<List<FilmDataModel>>

    @GET("films")
    suspend fun getFilm(@Path("id")id: String): Response<FilmDataModel>
}