package omarjarid.studioghibliapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import omarjarid.example.data.NetworkDatasource
import omarjarid.studioghibliapp.framework.network.NetworkDatasourceImpl
import javax.inject.Singleton

/*
     Nei casi in cui non è possibile effettuare la Constructor Injection, intervengono i moduli,
     cioè classi con le annotazioni @Module e @InstallIn (per dire a Hilt in quali componenti da
     esso generati installare i moduli).

     Il SingletonComponent è già definito in Hilt, e sostanzialmente il suo scope è in tutto ciò che
     è annotato con @Singleton.
*/
@Module
@InstallIn(SingletonComponent::class)

// internal = visibilità limitata al modulo in cui questa classe si trova (nel nostro caso app).
internal abstract class NetworkModule {
    /*
        L'annotazione @Binds dice a Hilt quale implementazione usare quando deve fornire un'istanza
        di un'interfaccia (in questo caso quando devo fornire un'istanza di NetworkDatasource
        fornirò l'implementazione concreta, NetworkDatasourceImpl).
    */
    @Binds
    @Singleton
    abstract fun provideNetworkDatasource(ds: NetworkDatasourceImpl): NetworkDatasource
}