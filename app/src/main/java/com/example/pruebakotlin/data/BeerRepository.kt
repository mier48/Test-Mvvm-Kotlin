package com.example.pruebakotlin.data

import com.example.pruebakotlin.data.database.dao.BeerDao
import com.example.pruebakotlin.data.database.entities.BeerEntity
import com.example.pruebakotlin.data.model.BeerModel
import com.example.pruebakotlin.data.model.BeerProvider
import com.example.pruebakotlin.data.network.BeerService
import com.example.pruebakotlin.domain.model.Beer
import com.example.pruebakotlin.domain.model.toDomain
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val api: BeerService,
    private val beerProvider: BeerProvider,
    private val beerDao: BeerDao
) {

    suspend fun getBeersByName(name: String): List<Beer> {
        val response: List<BeerModel> = api.getBeers(name)
        beerProvider.beers = response
        val result = response.map { it.toDomain() }
        val fav = getFavoritesBeers()
        for (r in result) {
            for (f in fav) {
                if (r.id == f.id) {
                    r.fav = true
                }
            }
        }

        return result
    }

    suspend fun getAllBeers(): List<Beer> {
        val response: List<BeerModel> = api.getAllBeers()
        beerProvider.beers = response
        val result = response.map { it.toDomain() }
        val fav = getFavoritesBeers()
        for (r in result) {
            for (f in fav) {
                if (r.id == f.id) {
                    r.fav = true
                }
            }
        }

        return result
    }

    suspend fun getFavoritesBeers(): List<Beer> {
        val response = beerDao.getAllFavorites()
        return response.map { it.toDomain() }
    }

    suspend fun getFavoriteBeer(id: Int): Beer {
        val response = beerDao.getFavorite(id)
        return response.toDomain()
    }

    suspend fun addFavorite(beer: BeerEntity) {
        beerDao.insert(beer)
    }

    suspend fun removeFavorite(id: Int) {
        beerDao.remove(id)
    }
}