package ru.geekbrains.kotlin_professional.dictionary.presenter

import io.reactivex.Observable
import ru.geekbrains.kotlin_professional.dictionary.di.LOCAL_SOURCE
import ru.geekbrains.kotlin_professional.dictionary.di.REMOTE_SOURCE
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.model.data.SearchResult
import ru.geekbrains.kotlin_professional.dictionary.model.repository.IRepository
import ru.geekbrains.kotlin_professional.dictionary.presenter.base.IInteractor
import javax.inject.Inject
import javax.inject.Named

class MainInteractor @Inject constructor(
    @Named(REMOTE_SOURCE) private val remoteRepo: IRepository<List<SearchResult>>,
    @Named(LOCAL_SOURCE) private val localRepo: IRepository<List<SearchResult>>
) : IInteractor<DataModel> {

    override fun getData(word: String, fromRemote: Boolean): Observable<DataModel> {
        return if (fromRemote) {
            remoteRepo
        } else {
            localRepo
        }.getData(word).map { DataModel.Success(it) }
    }
}