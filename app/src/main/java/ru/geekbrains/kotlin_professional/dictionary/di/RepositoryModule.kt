package ru.geekbrains.kotlin_professional.dictionary.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.IDataSource
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.LocalDataSource
import ru.geekbrains.kotlin_professional.dictionary.model.data_source.RemoteDataSource
import ru.geekbrains.kotlin_professional.dictionary.model.repository.IRepository
import ru.geekbrains.kotlin_professional.dictionary.model.repository.Repository
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(REMOTE_SOURCE)
    fun providesRepositoryRemote(@Named(REMOTE_SOURCE) dataSourceRemote: IDataSource<List<SearchResult>>): IRepository<List<SearchResult>> =
        Repository(dataSourceRemote)

    @Provides
    @Singleton
    @Named(LOCAL_SOURCE)
    fun providesRepositoryLocal(@Named(LOCAL_SOURCE) dataSourceLocal: IDataSource<List<SearchResult>>): IRepository<List<SearchResult>> =
        Repository(dataSourceLocal)

    @Provides
    @Singleton
    @Named(REMOTE_SOURCE)
    internal fun providesDataSourceRemote(): IDataSource<List<SearchResult>> = RemoteDataSource()

    @Provides
    @Singleton
    @Named(LOCAL_SOURCE)
    internal fun providesDataSourceLocal(): IDataSource<List<SearchResult>> = LocalDataSource()
}