package ru.dragontino.androidtestproject.feature.car.factory

import ru.dragontino.androidtestproject.feature.car.model.Bicycle
import ru.dragontino.androidtestproject.feature.car.model.Vehicle

class BicycleFactory : VehicleFactory {
    override fun getVehicle(): Vehicle {
        return Bicycle(
            brand = "Stern",
            model = "X29",
            year = 2022,
            currentSpeed = 0,
            gearCount = 21,
            type = "city",
            hasBell = true,
            hasBasket = false
        )
    }
}