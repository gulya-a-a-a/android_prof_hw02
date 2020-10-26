package ru.geekbrains.kotlin_professional.dictionary.app

import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.geekbrains.kotlin_professional.dictionary.di.DaggerAppComponent
import javax.inject.Inject

class DictApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var _dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): DispatchingAndroidInjector<Any>? {
        return _dispatchingAndroidInjector;
    }

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }
}