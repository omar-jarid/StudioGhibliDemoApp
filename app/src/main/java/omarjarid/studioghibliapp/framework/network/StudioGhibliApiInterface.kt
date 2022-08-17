package omarjarid.studioghibliapp.framework.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface StudioGhibliApiInterface {
    @GET("films")
    suspend fun getAllFilms(): Response<List<FilmDataModel>>

    @GET("films")
    suspend fun getFilm(@Path("id") id: String): Response<FilmDataModel>
}