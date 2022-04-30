package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.data.database.entities.toDatabase
import com.example.pruebakotlin.domain.model.Beer
import javax.inject.Inject

class AddFavorite  @Inject constructor(private val repository: BeerRepository) {

    suspend operator fun invoke(beer: Beer) {
        return repository.addFavorite(beer.toDatabase())
    }
}