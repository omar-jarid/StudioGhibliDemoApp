package omarjarid.studioghibliapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import omarjarid.studioghibliapp.framework.network.StudioGhibliApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class RetrofitModule {
    /*
        L'annotazione @Provides dice a Hilt quale implementazione usare quando deve fornire
        un'istanza di una classe di una libreria esterna (in questo caso quando devo fornire
        un'istanza di OkHttpClient "as is").
    */
    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        // L'HttpLoggingInterceptor tiene traccia di richiesta e risposta...
        val interceptor = HttpLoggingInterceptor()

        //... in particolare dell'header e del body!
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): StudioGhibliApiInterface {
        // Retrofit crea un'implementazione dell'interfaccia tramite il builder.
        /*
            Ho specificato l'URL base dell'API e ho anche aggiunto un GsonConverter (per i JSON) e
            un OkHttpClient (come definito prima).
        */
        return Retrofit.Builder().baseUrl("https://ghibliapi.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(StudioGhibliApiInterface::class.java)
    }
}