package com.example.pruebakotlin.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pruebakotlin.domain.model.Beer

@Entity(tableName = "beers_table")
data class BeerEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Int,
    @ColumnInfo(name = "name") var title: String,
    @ColumnInfo(name = "image_url") var image: String?,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "abv") var abv: Float,
    @ColumnInfo(name = "fav") var fav: Boolean = true
)

fun Beer.toDatabase() = BeerEntity(id, title, image, description, abv, true)