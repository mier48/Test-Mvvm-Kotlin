package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.domain.model.Beer
import javax.inject.Inject

class GetFavoritesBeerById @Inject constructor(private val repository: BeerRepository) {

    suspend operator fun invoke(id: Int): Beer {
        return repository.getFavoriteBeer(id)
    }
}