package ru.dragontino.androidtestproject.app

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService2
import ru.dragontino.androidtestproject.di.AppComponent
import ru.dragontino.androidtestproject.di.DaggerAppComponent
import ru.dragontino.androidtestproject.feature.home.HomeComponentProvider
import ru.dragontino.androidtestproject.feature.home.di.DaggerHomeComponent
import ru.dragontino.androidtestproject.feature.home.di.HomeComponent
import ru.dragontino.androidtestproject.feature.home.di.HomeDependencies
import ru.dragontino.androidtestproject.workers.DeviceIsChargingWorker

class App : Application(), HomeComponentProvider {
    lateinit var appComponent: AppComponent
        private set

    val deviceIsChargingOneTimeWorkRequest by lazy {
        val chargingDeviceConstraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()

        OneTimeWorkRequestBuilder<DeviceIsChargingWorker>()
            .setConstraints(chargingDeviceConstraints)
            .build()
    }


    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()

        WorkManager.getInstance(this)
            .enqueue(deviceIsChargingOneTimeWorkRequest)
    }

    override fun provideHomeComponent(): HomeComponent {
        val homeDependencies = object : HomeDependencies {
            override val retrofitService1: RetrofitService1 = appComponent.retrofitService1
            override val retrofitService2: RetrofitService2 = appComponent.retrofitService2
            override val viewModelFactory: ViewModelProvider.Factory = appComponent.viewModelFactory
        }
        return DaggerHomeComponent.factory().create(homeDependencies)
    }
}