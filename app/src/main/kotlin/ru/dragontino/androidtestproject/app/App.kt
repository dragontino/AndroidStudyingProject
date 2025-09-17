package ru.dragontino.androidtestproject.app

import android.app.Application
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.dragontino.androidtestproject.workers.DeviceIsChargingWorker

class App : Application() {
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

        WorkManager.getInstance(this)
            .enqueue(deviceIsChargingOneTimeWorkRequest)
    }
}