package omarjarid.studioghibliapp.framework.network

import omarjarid.example.data.NetworkDatasource
import omarjarid.example.domain.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDatasourceImpl @Inject constructor(
    private val studioGhibliApiInterface: StudioGhibliApiInterface
) : NetworkDatasource {
    override suspend fun getAllFilms(): List<Film> {
        val response = studioGhibliApiInterface.getAllFilms()
        return if (response.isSuccessful) response.body()!!.toDomainModel() else emptyList()
    }

    override suspend fun getFilm(id: String): Film {
        val response = studioGhibliApiInterface.getFilm(id)
        return if (response.isSuccessful) response.body()!!.toDomainModel() else Film()
    }
}