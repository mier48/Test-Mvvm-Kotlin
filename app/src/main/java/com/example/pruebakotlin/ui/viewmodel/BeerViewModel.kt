package com.example.pruebakotlin.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pruebakotlin.domain.*
import com.example.pruebakotlin.domain.model.Beer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BeerViewModel @Inject constructor(
    private val getBeers: GetBeers,
    private val getBeerById: GetBeerById,
    private val addFavorite: AddFavorite,
    private val removeFavorite: RemoveFavorite,
    private val getFavoritesBeers: GetFavoritesBeers
) : ViewModel() {

    var favBeerListModel = MutableLiveData<List<Beer>>()
    var beerListModel = MutableLiveData<List<Beer>>()
    var beerModel = MutableLiveData<Beer>()
    val isLoading = MutableLiveData<Boolean>()
    private var allItems: List<Beer> = ArrayList()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getBeers()
            allItems = result

            if (!result.isNullOrEmpty()) {
                beerListModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun onCreate(id: Int) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getBeerById(id)

            if (result != null) {
                beerModel.postValue(result)
                isLoading.postValue(false)
            }
        }
    }

    fun byName(name: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getBeers(name)
            allItems = result

            beerListModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun all() {
        viewModelScope.launch {
            isLoading.postValue(true)

            val result = getBeers()
            allItems = result

            beerListModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun addToFavorite(beer: Beer) {
        viewModelScope.launch {
            addFavorite.invoke(beer)

            for (i in allItems) {
                if (i.id == beer.id) {
                    i.fav = true
                }
            }

            beerListModel.postValue(allItems)
        }
    }

    fun deleteFavorite(beer: Beer) {
        viewModelScope.launch {
            removeFavorite.invoke(beer)

            for (i in allItems) {
                if (i.id == beer.id) {
                    i.fav = false
                }
            }

            beerListModel.postValue(allItems)
        }
    }

    fun favoritesBeers() {
        viewModelScope.launch {

            val result = getFavoritesBeers()
            favBeerListModel.postValue(result)
        }
    }
}