package omarjarid.example.domain.model

data class Film(
    // ID univoco che rappresenta un film specifico.
    val id: String = "",

    // Titolo del film.
    val title: String = "",

    // Titolo originale del film.
    val originalTitle: String = "",

    // Titolo originale del film in romaji.
    val originalTitleRomanised: String = "",

    // Descrizione del film.
    val description: String = "",

    // Regista del film.
    val director: String = "",

    // Produttore del film.
    val producer: String = "",

    // Anno di uscita del film.
    val releaseDate: String = "",

    // Durata del film in minuti.
    val runningTime: String = "",

    // Punteggio del film su Rotten Tomatoes.
    val rtScore: String = "",

    // Personaggi del film.
    /*
        Notare che la documentazione dice che si tratta di array di stringhe.
        E' però più saggio usare le liste perché gli array sono mutabili, le liste no
        (a meno che il tipo non sia MutableList, ma non è questo il nostro caso nel model).
    */
    val people: List<String> = emptyList(),

    // Specie dei personaggi del film.
    val species: List<String> = emptyList(),

    // Località presenti nel film.
    val locations: List<String> = emptyList(),

    // Veicoli che compaiono nel film.
    val vehicles: List<String> = emptyList(),

    // URL del film.
    val url: String = "",

    // Locandina del film.
    val image: String = "",

    // Banner del film.
    val movieBanner: String = "" 
)