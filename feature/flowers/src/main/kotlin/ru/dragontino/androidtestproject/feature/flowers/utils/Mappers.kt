package ru.dragontino.androidtestproject.feature.flowers.utils

import ru.dragontino.androidtestproject.core.room.entity.BouquetEntity
import ru.dragontino.androidtestproject.core.room.entity.FlowerEntity
import ru.dragontino.androidtestproject.core.room.entity.FlowerSet
import ru.dragontino.androidtestproject.feature.flowers.model.Bouquet
import ru.dragontino.androidtestproject.feature.flowers.model.Flower


fun Flower.mapToEntity(quantity: Int) = FlowerEntity(
    id = id,
    name = name,
    quantity = quantity
)


fun FlowerEntity.mapToFlower() = Flower(
    id = id,
    name = name
)


fun Bouquet.mapToBouquetEntity() = BouquetEntity(
    id = id,
    name = name,
    price = price
)


fun Bouquet.getFlowerSets(bouquetId: Long = this.id) = flowers.map { (flower, count) ->
    FlowerSet(
        id = 0,
        flowerId = flower.id,
        bouquetId = bouquetId,
        count = count
    )
}

