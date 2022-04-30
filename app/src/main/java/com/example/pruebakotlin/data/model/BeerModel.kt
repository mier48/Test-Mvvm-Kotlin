package com.example.pruebakotlin.data.model

import com.google.gson.annotations.SerializedName

data class BeerModel(
    @SerializedName("id") var id: Int,
    @SerializedName("name") var title: String,
    @SerializedName("image_url") var image: String?,
    @SerializedName("description") var description: String,
    @SerializedName("abv") var abv: Float,
    var fav: Boolean = false
)