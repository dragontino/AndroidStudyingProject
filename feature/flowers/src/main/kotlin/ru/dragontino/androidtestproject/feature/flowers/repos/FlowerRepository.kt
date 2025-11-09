package ru.dragontino.androidtestproject.feature.flowers.repos

import kotlinx.coroutines.flow.Flow
import ru.dragontino.androidtestproject.feature.flowers.model.Bouquet
import ru.dragontino.androidtestproject.feature.flowers.model.Flower

interface FlowerRepository {
    suspend fun addFlower(flower: Flower, quantity: Int): Result<Long>

    suspend fun updateFlower(flower: Flower): Result<Unit>

    suspend fun deleteFlowerById(id: Long): Result<Unit>

    suspend fun addBouquet(bouquet: Bouquet): Result<Long>

    suspend fun buyBouquet(bouquet: Bouquet): Result<Unit>

    fun fetchAllBouquets(): Flow<List<Bouquet>>

    suspend fun getBouquetsCount(): Result<Int>

    suspend fun updateBouquet(bouquet: Bouquet): Result<Unit>

    suspend fun deleteBouquet(bouquet: Bouquet): Result<Unit>
}