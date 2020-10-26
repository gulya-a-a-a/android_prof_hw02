package ru.geekbrains.kotlin_professional.dictionary.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ru.geekbrains.kotlin_professional.dictionary.app.DictApp
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(dictionaryApp: DictApp)
}