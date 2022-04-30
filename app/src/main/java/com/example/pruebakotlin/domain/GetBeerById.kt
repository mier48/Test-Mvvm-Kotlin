package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.model.BeerProvider
import com.example.pruebakotlin.domain.model.Beer
import com.example.pruebakotlin.domain.model.toDomain
import javax.inject.Inject

class GetBeerById @Inject constructor(private val beerProvider: BeerProvider) {

    operator fun invoke(id: Int): Beer? {
        val beers = beerProvider.beers
        if (!beers.isNullOrEmpty()) {
            for (beer in beers) {
                if (beer.id == id) {
                    return beer.toDomain()
                }
            }
        }
        return null
    }
}