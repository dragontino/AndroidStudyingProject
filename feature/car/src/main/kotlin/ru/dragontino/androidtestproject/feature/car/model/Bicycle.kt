package ru.dragontino.androidtestproject.feature.car.model

data class Bicycle(
    override val brand: String,
    override val model: String,
    override val year: Int,
    override val currentSpeed: Int,
    val gearCount: Int,
    val type: String,
    val hasBell: Boolean,
    val hasBasket: Boolean
) : Vehicle
