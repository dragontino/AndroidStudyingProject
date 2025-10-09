package ru.dragontino.androidtestproject.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService2
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val service1: RetrofitService1,
    private val service2: RetrofitService2
) : ViewModel() {

    init {
        viewModelScope.launch {
            launch {
                service1.getAll()
            }

            launch {
                service2.getAll()
            }
        }
    }
}