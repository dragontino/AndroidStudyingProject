package ru.dragontino.androidtestproject.feature.flowers.di

import dagger.Binds
import dagger.Module
import ru.dragontino.androidtestproject.feature.flowers.repos.FlowerRepository
import ru.dragontino.androidtestproject.feature.flowers.repos.FlowerRepositoryImpl

@Module
abstract class FlowersModule {
    @Binds
    internal abstract fun bindsFlowerRepository(repository: FlowerRepositoryImpl): FlowerRepository
}