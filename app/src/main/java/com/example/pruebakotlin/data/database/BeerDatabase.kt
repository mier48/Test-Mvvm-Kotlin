package com.example.pruebakotlin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebakotlin.data.database.dao.BeerDao
import com.example.pruebakotlin.data.database.entities.BeerEntity

@Database(entities = [BeerEntity::class], version = 1)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun getBeerDao(): BeerDao
}