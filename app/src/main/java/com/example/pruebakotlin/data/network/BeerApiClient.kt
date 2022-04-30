package com.example.pruebakotlin.data.network

import com.example.pruebakotlin.data.model.BeerModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApiClient {
    @GET("beers")
    suspend fun getBeers(@Query("beer_name") name: String): Response<List<BeerModel>>

    @GET("beers")
    suspend fun getAllBeers(): Response<List<BeerModel>>
}