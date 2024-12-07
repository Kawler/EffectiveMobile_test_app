package com.kawler.effmobile.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kawler.effmobile.domain.models.PopularCity
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularCityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: PopularCity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCities(cities: List<PopularCity>)

    @Query("SELECT * FROM PopularCity")
    fun getAllCities(): Flow<List<PopularCity>>

    @Query("SELECT * FROM PopularCity WHERE id = :id")
    suspend fun getCityById(id: Int): PopularCity?

    @Delete
    suspend fun deleteCity(city: PopularCity)

    @Query("DELETE FROM PopularCity")
    suspend fun deleteAllCities()
}