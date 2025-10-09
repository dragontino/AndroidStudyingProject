package ru.dragontino.androidtestproject.feature.home.di

import androidx.lifecycle.ViewModelProvider
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService2

interface HomeDependencies {
    val retrofitService1: RetrofitService1
    val retrofitService2: RetrofitService2
    val viewModelFactory: ViewModelProvider.Factory
}
