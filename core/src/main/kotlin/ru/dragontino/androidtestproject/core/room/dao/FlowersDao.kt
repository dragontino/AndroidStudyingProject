package ru.dragontino.androidtestproject.core.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.dragontino.androidtestproject.core.room.entity.FlowerEntity

@Dao
interface FlowersDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFlower(flower: FlowerEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFlowers(flowers: List<FlowerEntity>): List<Long>

    @Query("UPDATE Flower SET name = :newName WHERE id = :flowerId")
    suspend fun updateFlowerName(flowerId: Long, newName: String): Int

    @Query("UPDATE Flower set quantity = quantity - :count WHERE id = :flowerId")
    suspend fun decreaseQuantity(flowerId: Long, count: Int): Int

    @Query("SELECT * FROM Flower WHERE id = :id")
    suspend fun getFlowerById(id: Long): FlowerEntity?

    @Query("SELECT * FROM Flower")
    suspend fun getAllFlowers(): List<FlowerEntity>

    @Query("DELETE FROM Flower WHERE id = :id")
    suspend fun deleteFlowerById(id: Long): Int
}