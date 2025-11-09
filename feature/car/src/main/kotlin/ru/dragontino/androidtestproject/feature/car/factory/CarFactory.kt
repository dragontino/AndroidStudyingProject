package ru.dragontino.androidtestproject.feature.car.factory

import ru.dragontino.androidtestproject.feature.car.model.Car
import ru.dragontino.androidtestproject.feature.car.model.Vehicle

internal class CarFactory : VehicleFactory {
    override fun getVehicle(): Vehicle {
        return Car.Builder()
            .setBrand("Audi")
            .setModel("A5")
            .setYear(2018)
            .setColor("#FF00FF")
            .build()
    }
}