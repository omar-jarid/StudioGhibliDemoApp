package omarjarid.studioghibliapp.framework.network

import omarjarid.example.domain.model.Film

// DataModel nello strato più esterno, è il tipo di ritorno che voglio nella risposta HTTP...
data class FilmDataModel(
    val description: String? = "",
    val director: String? = "",
    val id: String? = "",
    val image: String? = "",
    val locations: List<String>? = listOf(),
    val movie_banner: String? = "",
    val original_title: String? = "",
    val original_title_romanised: String? = "",
    val people: List<String>? = listOf(),
    val producer: String? = "",
    val release_date: String? = "",
    val rt_score: String? = "",
    val running_time: String? = "",
    val species: List<String>? = listOf(),
    val title: String? = "",
    val url: String? = "",
    val vehicles: List<String>? = listOf()
)

//... perché a partire da esso creerò una classe del Model!
fun FilmDataModel.toDomainModel(): Film = Film(
    movieBanner = this.movie_banner.orEmpty(),
    description = this.description.orEmpty(),
    director = this.director.orEmpty(),
    id = this.id.orEmpty(),
    image = this.image.orEmpty(),
    locations = this.locations.orEmpty(),
    originalTitle = this.original_title.orEmpty(),
    originalTitleRomanised = this.original_title_romanised.orEmpty(),
    people = this.people.orEmpty(),
    producer = this.producer.orEmpty(),
    releaseDate = this.release_date.orEmpty(),
    rtScore = this.rt_score.orEmpty(),
    runningTime = this.running_time.orEmpty(),
    species = this.species.orEmpty(),
    title = this.title.orEmpty(),
    url = this.url.orEmpty(),
    vehicles = this.vehicles.orEmpty()
)

// Qui nel caso fosse richiesta una lista, la vado a creare.
fun List<FilmDataModel>.toDomainModel(): List<Film> = this.map { it.toDomainModel() }