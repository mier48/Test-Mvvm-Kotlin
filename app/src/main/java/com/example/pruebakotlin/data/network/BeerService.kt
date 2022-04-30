package com.example.pruebakotlin.data.network

import com.example.pruebakotlin.data.model.BeerModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerService @Inject constructor(private val api: BeerApiClient) {

    suspend fun getBeers(name: String): List<BeerModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getBeers(name)
            response.body() ?: emptyList()
        }
    }

    suspend fun getAllBeers(): List<BeerModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllBeers()
            response.body() ?: emptyList()
        }
    }
}