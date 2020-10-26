package ru.geekbrains.kotlin_professional.dictionary.view.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel

abstract class BaseViewModel<T : DataModel>(
    protected var _liveDataForView: MutableLiveData<T> = MutableLiveData(),
    protected var _compositeDisposable: CompositeDisposable = CompositeDisposable()
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = _liveDataForView

    override fun onCleared() {
        _compositeDisposable.dispose()
    }
}