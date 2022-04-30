package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.data.database.entities.toDatabase
import com.example.pruebakotlin.data.model.BeerModel
import com.example.pruebakotlin.data.model.BeerProvider
import com.example.pruebakotlin.domain.model.Beer
import com.example.pruebakotlin.domain.model.toDomain
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetBeerByIdTest {

    @RelaxedMockK
    private lateinit var provider: BeerProvider

    lateinit var getBeerById: GetBeerById

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBeerById = GetBeerById(provider)
    }

    @Test
    fun `when data from api is empty then return null`() = runBlocking{
        //Given
        coEvery { provider.beers } returns emptyList()

        //When
        val response = getBeerById(1)

        //Then
        assert(response == null)
    }

    @Test
    fun `when data from api is not empty then return one beer`() = runBlocking{
        //Given
        val myList = listOf(BeerModel(1, "Imperial Ipa", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 9.5f, false))
        coEvery { provider.beers } returns myList

        //When
        val response = getBeerById(1)

        //Then
        assert(response == myList.first().toDomain())
    }
}