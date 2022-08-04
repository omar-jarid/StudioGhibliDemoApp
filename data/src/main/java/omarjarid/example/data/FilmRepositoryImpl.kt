package omarjarid.example.data

import omarjarid.example.domain.model.Film
import omarjarid.example.domain.repository.FilmRepository
import javax.inject.Inject

/*
    Nel package "impl" vado a dare un'implementazione concreta alle interfacce del package
    "domain.repository".

    Inoltre, con l'annotazione @Inject (e poi l'uso di Hilt) sto segnando che qui avviene
    l'iniezione del parametro nel costruttore.

    DESIGN PATTERN -- DEPENDENCY INJECTION

    CAMPO DI APPLICAZIONE: creazionale

    PROBLEMA: si definisce dipendenza un qualsiasi oggetto di cui una classe ha bisogno per
    funzionare.
    Vogliamo RIDURRE LA QUANTITA' di dipendenze dannose tra classi. In particolar modo introduciamo
    dipendenze dannose quando creiamo un oggetto di tipo B all'interno della classe A.

    SOLUZIONE: ci sono vari tipi di Dependency Injection, qui analizzo quello più comune, cioè la
    Constructor Injection.
    L'idea è quella di passare l'oggetto B nel costruttore di A, portando quindi i seguenti vantaggi:

    - se prima A doveva creare anche l'oggetto B, ora fa solo una cosa
      (principio di Single Responsibility (*))

    - ciò aumenta il disaccoppiamento tra classi e slega quindi la creazione di B da quella di A

    - per fare una cosa fatta meglio, B deve essere un'interfaccia; questo non solo rispetta il
      principio di Dependency Inversion (*), ma permette di iniettare le dipendenze a runtime,
      variando quindi il comportamento del programma.

    E se io avessi la dipendenza di una dipendenza di una dipendenza...?
    Problema troppo complesso da gestire manualmente: usiamo un container (nel nostro caso Hilt, che
    tratterò più avanti).

    In questo caso io sto iniettando nel costruttore l'interfaccia NetworkDatasource, di cui userò
    i metodi. Con l'annotazione @Inject io sto dicendo a Hilt che in questo punto avverrà
    l'iniezione della dipendenza (ci pensa Hilt sottobanco).
*/

class FilmRepositoryImpl @Inject constructor(
    private val networkDatasource: NetworkDatasource
) : FilmRepository {
    // Notare la sintassi. Se ho solo un'istruzione di return posso scrivere anche con l'uguale.
    // Questa sintassi è perfettamente equivalente a quella alla Java dove si mette solo il return.
    override suspend fun getAllFilms(): List<Film> = networkDatasource.getAllFilms()
    override suspend fun getFilm(id: String): Film = networkDatasource.getFilm(id)
}

/*
    (*)
    SOLID PRINCIPLES
    Si tratta di 5 princìpi di progettazione che rendono il software design più comprensibile,
    flessibile e manutenibile:

    1) SINGLE RESPONSIBILITY: una classe deve avere uno e un solo compito (quindi una e una sola
       ragione di cambiare

    2) OPEN-CLOSED: le classi devono essere aperte all'estensione e chiuse alla modifica, cioè
       bisogna evitare di modificare codice già esistente e provare ad estendere le classi

    3) LISKOV SUBSTITUTION: se si ha una classe di un tipo e un qualsiasi numero di sue sottoclassi,
       queste devono poter essere sostituibili dalla classe madre senza compromettere il
       comportamento del codice

    4) INTERFACE SEGREGATION: meglio avere tante piccole interfacce specifiche che una più grande e
       generica, per evitare che una classe debba implementare metodi non necessari

    5) DEPENDENCY INVERSION: i componenti devono dipendere dalle astrazioni e non dalle
       implementazioni concrete
*/