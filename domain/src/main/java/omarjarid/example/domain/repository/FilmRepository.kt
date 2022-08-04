package omarjarid.example.domain.repository

import omarjarid.example.domain.model.Film

/*
 Nel package "repository" vado a mettere delle interfacce che creano uno strato di astrazione.
 Questo strato ha lo scopo di disaccoppiare la logica di business (le data class) dal modulo data
 (che sarà quello immediatamente più esterno e dipenderà da questo modulo domain).

 Le implementazioni concrete di queste interfacce saranno realizzate nel modulo data.

 Nota che c'è una corrispondenza 1:1 tra le interfacce di questo package e le data class del model.
*/
interface FilmRepository {
    /*
        Le suspend functions sono funzioni che possono essere avviate, messe in pausa e riprese.
        Possono essere chiamate soltanto da una coroutine o da un'altra suspend function.

        Analizzerò le coroutine in un'altra parte dell'app.

        Le funzioni rispecchiano le chiamate all'API e i parametri rispecchiano quelli della
        documentazione.
    */
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilm(id: String): Film
}