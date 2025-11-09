package ru.dragontino.androidtestproject.feature.flowers.repos

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.dragontino.androidtestproject.core.room.dao.BouquetsDao
import ru.dragontino.androidtestproject.core.room.dao.FlowersDao
import ru.dragontino.androidtestproject.feature.flowers.model.Bouquet
import ru.dragontino.androidtestproject.feature.flowers.model.Flower
import ru.dragontino.androidtestproject.feature.flowers.utils.getFlowerSets
import ru.dragontino.androidtestproject.feature.flowers.utils.mapToBouquetEntity
import ru.dragontino.androidtestproject.feature.flowers.utils.mapToEntity
import ru.dragontino.androidtestproject.feature.flowers.utils.mapToFlower
import javax.inject.Inject

internal class FlowerRepositoryImpl @Inject constructor(
    private val bouquetsDao: BouquetsDao,
    private val flowersDao: FlowersDao
) : FlowerRepository {

    override suspend fun addFlower(flower: Flower, quantity: Int): Result<Long> {
        val entity = flower.mapToEntity(quantity)
        val id = flowersDao.addFlower(entity)
        return Result.success(id)
    }

    override suspend fun updateFlower(flower: Flower): Result<Unit> {
        val updatedCount = flowersDao.updateFlowerName(flower.id, flower.name)
        return when {
            updatedCount < 1 -> Result.failure(Exception())
            else -> Result.success(Unit)
        }
    }

    override suspend fun deleteFlowerById(id: Long): Result<Unit> {
        val deletedCount = flowersDao.deleteFlowerById(id)
        return when {
            deletedCount < 1 -> Result.failure(Exception())
            else -> Result.success(Unit)
        }
    }

    override suspend fun addBouquet(bouquet: Bouquet): Result<Long> {
        val entity = bouquet.mapToBouquetEntity()
        val id = bouquetsDao.insertBouquet(entity)
        bouquetsDao.insertFlowerSets(bouquet.getFlowerSets(bouquetId = id))
        return Result.success(id)
    }

    override suspend fun buyBouquet(bouquet: Bouquet): Result<Unit> {
        val flowers = bouquetsDao.getFlowersForBouquet(bouquet.id)
        val success = coroutineScope {
            flowers.map { (flower, count) ->
                async { flowersDao.decreaseQuantity(flower.id, count) }
            }.awaitAll().all { it == 1 }
        }

        return when {
            success -> Result.success(Unit)
            else -> Result.failure(Exception())
        }
    }

    override fun fetchAllBouquets(): Flow<List<Bouquet>> {
        return bouquetsDao.fetchAllBouquets().map { list ->
            list.map {
                coroutineScope {
                    async {
                        val flowers = bouquetsDao
                            .getFlowersForBouquet(it.id)
                            .associate { (flowerEntity, count) -> flowerEntity.mapToFlower() to count }

                        Bouquet(
                            id = it.id,
                            name = it.name,
                            price = it.price,
                            flowers = flowers
                        )
                    }
                }
            }.awaitAll()
        }
    }

    override suspend fun getBouquetsCount(): Result<Int> {
        val count = bouquetsDao.getBouquetsCount()
        return Result.success(count)
    }

    override suspend fun updateBouquet(bouquet: Bouquet): Result<Unit> {
        val bouquetEntity = bouquet.mapToBouquetEntity()
        val flowerSets = bouquet.getFlowerSets()
        bouquetsDao.updateBouquetWithFlowers(bouquetEntity, flowerSets)
        return Result.success(Unit)
    }

    override suspend fun deleteBouquet(bouquet: Bouquet): Result<Unit> {
        bouquetsDao.deleteBouquetWithFlowers(bouquet.mapToBouquetEntity(), bouquet.getFlowerSets())
        return Result.success(Unit)
    }
}