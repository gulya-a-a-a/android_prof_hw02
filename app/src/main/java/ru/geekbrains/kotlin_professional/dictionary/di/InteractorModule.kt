package ru.geekbrains.kotlin_professional.dictionary.di

import dagger.Module
import dagger.Provides
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult
import ru.geekbrains.kotlin_professional.dictionary.model.repository.IRepository
import ru.geekbrains.kotlin_professional.dictionary.presenter.MainInteractor
import javax.inject.Named

@Module
class InteractorModule {
    @Provides
    internal fun provideInteractor(
        @Named(REMOTE_SOURCE) repositoryRemote: IRepository<List<SearchResult>>,
        @Named(LOCAL_SOURCE) repositoryLocal: IRepository<List<SearchResult>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}