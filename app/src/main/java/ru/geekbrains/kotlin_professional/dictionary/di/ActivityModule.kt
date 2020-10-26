package ru.geekbrains.kotlin_professional.dictionary.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import ru.geekbrains.kotlin_professional.dictionary.view.activity.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}