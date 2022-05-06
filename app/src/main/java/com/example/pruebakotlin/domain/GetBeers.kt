package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.domain.model.Beer
import javax.inject.Inject

class GetBeers @Inject constructor(private val repository: BeerRepository) {

    suspend operator fun invoke(): List<Beer> {
        return repository.getAllBeers()
    }
}