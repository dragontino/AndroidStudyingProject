package ru.dragontino.androidtestproject.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.dragontino.androidtestproject.core.di.CoreModule
import ru.dragontino.androidtestproject.feature.flowers.di.FlowersModule
import ru.dragontino.androidtestproject.feature.home.di.HomeDependencies
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class, ViewModelModule::class, FlowersModule::class]
)
interface AppComponent {
    val homeDependencies: HomeDependencies

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}