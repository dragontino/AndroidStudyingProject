package ru.dragontino.androidtestproject.feature.home.di

import dagger.Component
import ru.dragontino.androidtestproject.feature.home.HomeFragment

@HomeScope
@Component(dependencies = [HomeDependencies::class])
interface HomeComponent {
    fun inject(fragment: HomeFragment)

    @Component.Factory
    interface Factory {
        fun create(dependencies: HomeDependencies): HomeComponent
    }
}