package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.domain.model.Beer
import javax.inject.Inject

class GetBeersByName @Inject constructor(private val repository: BeerRepository) {

    suspend operator fun invoke(name: String): List<Beer> {
        return repository.getBeersByName(name)
    }
}