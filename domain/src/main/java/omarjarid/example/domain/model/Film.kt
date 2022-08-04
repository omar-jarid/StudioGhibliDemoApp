package omarjarid.example.domain.model

/*
    Nel package "model" vado a inserire le classi che serviranno da "contenitori" dei dati
    recuperati dall'API, costruite secondo la documentazione.

    In Kotlin usiamo le data class, cioè classi che hanno lo scopo di contenere dati.
    Hanno il vantaggio di farci portare a casa automaticamente i seguenti metodi per ogni campo
    dichiarato nel costruttore:

    - equals()
    - hashCode()
    - toString(), del tipo Tipo(campo1=valore1, ..., campo_n=valore_n)
    - funzione componentN() che corrisponde al N-esimo componente in ordine di dichiarazione
    - copy(), che serve per copiare un oggetto ma permette al contempo di alterare ALCUNI dei campi.
*/

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
    val image: String = ""
)

/*
    In Kotlin la visibilità di default è public. Posso anche fregarmene, dato che dichiaro le
    variabili in sola lettura con la parola chiave val.

    Inoltre ho assegnato un valore di default vuoto a ciascuna variabile dichiarata nel costruttore,
    per essere sicuro che abbiano tutte un valore anche se non dovessi per qualche motivo riuscire a
    riempirle.
*/