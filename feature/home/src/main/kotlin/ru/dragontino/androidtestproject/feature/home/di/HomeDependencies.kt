package ru.dragontino.androidtestproject.feature.home.di

import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class HomeDependencies @Inject constructor(
    val viewModelFactory: ViewModelProvider.Factory
)
