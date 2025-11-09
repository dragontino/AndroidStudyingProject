package ru.dragontino.androidtestproject.core.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.dragontino.androidtestproject.core.room.entity.BouquetEntity
import ru.dragontino.androidtestproject.core.room.entity.FlowerInBouquet
import ru.dragontino.androidtestproject.core.room.entity.FlowerSet

@Dao
interface BouquetsDao {
    @Query("SELECT * FROM Bouquet")
    fun fetchAllBouquets(): Flow<List<BouquetEntity>>

    @Transaction
    @Query(
        """
            SELECT f.*, fs.count FROM Flower AS f
            INNER JOIN FlowerSet AS fs ON fs.flowerId = f.id
            WHERE fs.bouquetId = :bouquetId
        """
    )
    suspend fun getFlowersForBouquet(bouquetId: Long): List<FlowerInBouquet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBouquet(bouquet: BouquetEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlowerSets(flowerSets: List<FlowerSet>): List<Long>

    @Transaction
    suspend fun updateBouquetWithFlowers(bouquet: BouquetEntity, flowerSets: List<FlowerSet>) {
        updateBouquet(bouquet)
        updateFlowerSets(flowerSets)
    }

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBouquet(bouquet: BouquetEntity): Int

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFlowerSets(flowerSet: List<FlowerSet>): Int


    @Transaction
    suspend fun deleteBouquetWithFlowers(bouquet: BouquetEntity, flowerSets: List<FlowerSet>) {
        deleteBouquet(bouquet)
        deleteFlowerSets(flowerSets)
    }

    @Delete
    suspend fun deleteBouquet(bouquet: BouquetEntity): Int

    @Delete
    suspend fun deleteFlowerSets(flowerSets: List<FlowerSet>): Int

    @Query("SELECT COUNT(id) FROM Bouquet")
    suspend fun getBouquetsCount(): Int
}