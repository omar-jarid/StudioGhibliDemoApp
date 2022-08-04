package omarjarid.studioghibliapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*
    HILT

    Hilt è una libreria di Android che riduce la scrittura manuale della Dependency Injection nel
    progetto, fornendo di fatto una maniera standard di gestirla automaticamente e in maniera
    elegante.

    TUTTE le app che fanno uso di Hilt devono contenere una classe di tipo Application (che può
    tranquillamente essere subclassata, come in questo caso), annotata con @HiltAndroidApp.

    Quest'annotazione innesca la generazione di codice di Hilt includendo una classe base per l'app
    che funge da contenitore delle dipendenze e da padre per gli altri componenti, che avranno
    accesso a tali dipendenze.
*/

@HiltAndroidApp
class StudioGhibliApp : Application()