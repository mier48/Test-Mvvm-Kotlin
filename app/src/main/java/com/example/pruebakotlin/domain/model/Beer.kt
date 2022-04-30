package com.example.pruebakotlin.domain.model

import com.example.pruebakotlin.data.database.entities.BeerEntity
import com.example.pruebakotlin.data.model.BeerModel

data class Beer(
    val id: Int,
    val title: String,
    val image: String?,
    val description: String,
    val abv: Float,
    var fav: Boolean
)

fun BeerModel.toDomain() = Beer(id, title, image, description, abv, fav)
fun BeerEntity.toDomain() = Beer(id, title, image, description, abv, fav)