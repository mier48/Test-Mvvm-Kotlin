package com.example.pruebakotlin.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebakotlin.data.database.entities.BeerEntity

@Dao
interface BeerDao {

    @Query("SELECT * FROM beers_table ORDER BY name ASC")
    suspend fun getAllFavorites(): List<BeerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: BeerEntity)

    @Query("DELETE FROM beers_table WHERE id = :beerId")
    suspend fun remove(beerId: Int)

    @Query("SELECT * FROM beers_table WHERE id = :beerId")
    suspend fun getFavorite(beerId: Int): BeerEntity
}