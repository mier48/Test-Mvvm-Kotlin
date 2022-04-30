package com.example.pruebakotlin.di

import android.content.Context
import androidx.room.Room
import com.example.pruebakotlin.data.database.BeerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val BEER_DATABASE_NAME = "beer_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, BeerDatabase::class.java, BEER_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideBeerDao(db: BeerDatabase) = db.getBeerDao()
}