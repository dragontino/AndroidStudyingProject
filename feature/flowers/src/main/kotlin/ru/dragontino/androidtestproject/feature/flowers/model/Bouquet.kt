package ru.dragontino.androidtestproject.feature.flowers.model

data class Bouquet(
    val id: Long,
    val name: String,
    val price: Double,
    val flowers: Map<Flower, Int>
)
