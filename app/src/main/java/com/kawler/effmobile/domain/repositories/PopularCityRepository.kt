package com.kawler.effmobile.domain.repositories

import com.kawler.effmobile.domain.models.PopularCity
import kotlinx.coroutines.flow.Flow

interface PopularCityRepository {
    fun getAllCities(): Flow<List<PopularCity>>
    suspend fun checkAndInsertCitiesIfNeeded()
}