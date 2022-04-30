package com.example.pruebakotlin.domain

import com.example.pruebakotlin.data.BeerRepository
import com.example.pruebakotlin.domain.model.Beer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetBeersTest {

    @RelaxedMockK
    private lateinit var repository: BeerRepository

    lateinit var getBeers: GetBeers

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getBeers = GetBeers(repository)
    }

    @Test
    fun `when the api return something then return values from api`() = runBlocking {
        //
        val myList = listOf(Beer(1, "Imperial Ipa", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 9.5f, false))
        coEvery { repository.getAllBeers() } returns myList

        //When
        val response = getBeers()

        //Then
        //coVerify(exactly = 1) {  }
        assert(myList == response)
    }
}