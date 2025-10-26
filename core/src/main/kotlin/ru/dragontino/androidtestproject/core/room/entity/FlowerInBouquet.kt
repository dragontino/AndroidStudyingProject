package ru.dragontino.androidtestproject.core.room.entity

import androidx.room.Embedded

data class FlowerInBouquet(
    @Embedded val flower: FlowerEntity,
    val count: Int
)
