package ru.dragontino.androidtestproject.feature.car.model

class Car private constructor(
    override val brand: String,
    override val model: String,
    override val year: Int,
    override val currentSpeed: Int,
    val color: String,
    val vin: String,
    val transmission: String,
    val mileage: Long,
) : Vehicle {


    class Builder {
        var brand = ""
        var model = ""
        var year = 2025
        var currentSpeed = 0
        var color = ""
        var vin = ""
        var transmission = ""
        var mileage = 0L


        fun setBrand(brand: String): Builder = apply { this.brand = brand }
        fun setModel(model: String): Builder = apply { this.model = model }
        fun setYear(year: Int): Builder = apply { this.year = year }
        fun setSpeed(speed: Int): Builder = apply { this.currentSpeed = speed }
        fun setColor(color: String): Builder = apply { this.color = color }
        fun setVin(vin: String): Builder = apply { this.vin = vin }
        fun setTransmission(transmission: String): Builder = apply { this.transmission = transmission }
        fun setMileage(mileage: Long): Builder = apply { this.mileage = mileage }

        fun build() = Car(
            brand = brand,
            model = model,
            year = year,
            currentSpeed = currentSpeed,
            color = color,
            vin = vin,
            transmission = transmission,
            mileage = mileage,
        )
    }
}


inline fun buildCar(builder: Car.Builder.() -> Unit): Car {
    return Car.Builder().apply(builder).build()
}