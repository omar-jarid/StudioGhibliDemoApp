package omarjarid.studioghibliapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import omarjarid.example.data.*
import omarjarid.example.domain.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun provideFilmRepository(repo: FilmRepositoryImpl) : FilmRepository
}