package ru.dragontino.androidtestproject.core.retrofit

import retrofit2.http.GET

interface RetrofitService1 {
    @GET("")
    suspend fun getAll(): String
}


interface RetrofitService2 {
    @GET("")
    suspend fun getAll(): String
}