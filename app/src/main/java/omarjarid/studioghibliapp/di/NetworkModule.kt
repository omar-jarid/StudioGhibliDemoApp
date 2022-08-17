package omarjarid.studioghibliapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import omarjarid.example.data.NetworkDatasource
import omarjarid.studioghibliapp.framework.network.NetworkDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NetworkModule {
    @Binds
    @Singleton
    abstract fun provideNetworkDatasource(ds: NetworkDatasourceImpl): NetworkDatasource
}