package ru.dragontino.androidtestproject.core.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Bouquet")
data class BouquetEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val name: String,
    val price: Double
)
