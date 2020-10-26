package ru.geekbrains.kotlin_professional.dictionary.view.view_model

import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import ru.geekbrains.kotlin_professional.dictionary.model.data.DataModel
import ru.geekbrains.kotlin_professional.dictionary.presenter.MainInteractor
import ru.geekbrains.kotlin_professional.dictionary.view.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(private val _interactor: MainInteractor) :
    BaseViewModel<DataModel>() {

    private var _dataModel: DataModel? = null

    fun subscribe(): LiveData<DataModel> = _liveDataForView

    override fun getData(word: String, isOnline: Boolean): LiveData<DataModel> {
        _compositeDisposable.add(
            _interactor.getData(word, isOnline)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { _liveDataForView.value = DataModel.Loading(null) }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun getObserver(): DisposableObserver<DataModel> {
        return object : DisposableObserver<DataModel>() {
            override fun onNext(t: DataModel) {
                _dataModel = t
                _liveDataForView.value = t
            }

            override fun onError(e: Throwable) {
                _liveDataForView.value = DataModel.Error(e)
            }

            override fun onComplete() {}
        }
    }
}