package ru.dragontino.androidtestproject.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Flower")
data class FlowerEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val quantity: Int
)
