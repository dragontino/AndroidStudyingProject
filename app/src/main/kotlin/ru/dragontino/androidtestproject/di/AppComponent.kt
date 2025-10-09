package ru.dragontino.androidtestproject.di

import androidx.lifecycle.ViewModelProvider
import dagger.Component
import ru.dragontino.androidtestproject.core.di.CoreModule
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService2
import javax.inject.Singleton

@Singleton
@Component(
    modules = [CoreModule::class, ViewModelModule::class]
)
interface AppComponent {
    val retrofitService1: RetrofitService1
    val retrofitService2: RetrofitService2
    val viewModelFactory: ViewModelProvider.Factory
}