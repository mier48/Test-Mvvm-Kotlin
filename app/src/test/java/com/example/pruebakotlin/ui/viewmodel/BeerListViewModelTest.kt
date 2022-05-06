package com.example.pruebakotlin.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pruebakotlin.domain.*
import com.example.pruebakotlin.domain.model.Beer
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class BeerListViewModelTest {

    @RelaxedMockK
    private lateinit var getBeers: GetBeers

    @RelaxedMockK
    private lateinit var getBeersByName: GetBeersByName

    @RelaxedMockK
    private lateinit var getBeerById: GetBeerById

    @RelaxedMockK
    private lateinit var addFavorite: AddFavorite

    @RelaxedMockK
    private lateinit var removeFavorite: RemoveFavorite

    @RelaxedMockK
    private lateinit var getFavoritesBeers: GetFavoritesBeers

    @RelaxedMockK
    private lateinit var getFavoritesBeerById: GetFavoritesBeerById

    private lateinit var beerListViewModel: BeerListViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        beerListViewModel =
            BeerListViewModel(getBeers, getBeersByName, getBeerById, addFavorite, removeFavorite, getFavoritesBeers, getFavoritesBeerById)

        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when viewmodel is created the first time, get all beers`() = runTest {
        //Given
        val beer1 = Beer(1, "Imperial Ipa", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 9.5f, false)
        val beer2 = Beer(2, "Imperial Ipa 2", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 7.5f, false)
        val myList = listOf(beer1, beer2)

        coEvery { getBeers() } returns myList

        //When
        beerListViewModel.onCreate()

        //Then
        assert(beerListViewModel.beerListModel.value == myList)
    }

    @Test
    fun `when byName return beers set on the livedata`() = runTest {
        //Given
        val beer1 = Beer(1, "Imperial Ipa", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 9.5f, false)
        val beer2 = Beer(2, "Imperial Ipa 2", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 7.5f, false)
        val myList = listOf(beer1, beer2)

        coEvery { getBeersByName("Ipa") } returns myList

        //When
        beerListViewModel.byName("Ipa")

        //Then
        assert(beerListViewModel.beerListModel.value == myList)
    }

    @Test
    fun `if byName return null return empty list`() = runTest {
        //Given
        val beer1 = Beer(1, "Imperial Ipa", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 9.5f, false)
        val beer2 = Beer(2, "Imperial Ipa 2", "https://images.punkapi.com/v2/4.png", "lorem ipsum dolor sit amet", 7.5f, false)
        val myList = listOf(beer1, beer2)

        coEvery { getBeersByName("stout") } returns emptyList()

        //When
        beerListViewModel.byName("stout")

        //Then
        assert(beerListViewModel.beerListModel.value != null)
    }
}