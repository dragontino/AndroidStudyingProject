package ru.dragontino.androidtestproject.core.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FlowerEntity::class,
            parentColumns = ["id"],
            childColumns = ["flowerId"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = BouquetEntity::class,
            parentColumns = ["id"],
            childColumns = ["bouquetId"],
            onDelete = ForeignKey.CASCADE,
            deferred = true
        )
    ],
    indices = [Index("flowerId"), Index("bouquetId")]
)
data class FlowerSet(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val flowerId: Long,
    val bouquetId: Long,
    val count: Int
)
