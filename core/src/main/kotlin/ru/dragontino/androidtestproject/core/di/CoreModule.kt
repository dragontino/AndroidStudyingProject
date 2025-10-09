package ru.dragontino.androidtestproject.core.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import ru.dragontino.androidtestproject.core.retrofit.RetrofitResources1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitResources2
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService1
import ru.dragontino.androidtestproject.core.retrofit.RetrofitService2
import javax.inject.Named
import javax.inject.Singleton

@Module
class CoreModule {

    @Singleton
    @Provides
    fun provideRetrofitService1(@Named("server_1") retrofit: Retrofit): RetrofitService1 {
        return retrofit.create(RetrofitService1::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService2(@Named("server_2") retrofit: Retrofit): RetrofitService2 {
        return retrofit.create(RetrofitService2::class.java)
    }

    @Provides
    @Singleton
    @Named("server_1")
    fun provideRetrofit1(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitResources1.BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    @Named("server_2")
    fun provideRetrofit2(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitResources2.BASE_URL)
            .build()
    }
}