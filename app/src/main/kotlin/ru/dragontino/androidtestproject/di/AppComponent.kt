package ru.dragontino.androidtestproject.di

import dagger.Component
import ru.dragontino.androidtestproject.core.di.CoreModule
import ru.dragontino.androidtestproject.feature.home.di.HomeDependencies
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class, ViewModelModule::class]
)
interface AppComponent {
    val homeDependencies: HomeDependencies
}