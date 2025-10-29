package ru.dragontino.androidtestproject.feature.car.factory

import ru.dragontino.androidtestproject.feature.car.model.Vehicle

interface VehicleFactory {
    fun getVehicle(): Vehicle
}