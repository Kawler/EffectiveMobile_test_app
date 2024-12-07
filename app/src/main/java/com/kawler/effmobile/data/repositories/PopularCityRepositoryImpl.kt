package com.kawler.effmobile.data.repositories

import com.kawler.effmobile.data.db.PopularCityDao
import com.kawler.effmobile.domain.models.PopularCity
import com.kawler.effmobile.domain.repositories.PopularCityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularCityRepositoryImpl @Inject constructor(
    private val dao: PopularCityDao
) : PopularCityRepository {
    override fun getAllCities(): Flow<List<PopularCity>> = dao.getAllCities()
    override suspend fun checkAndInsertCitiesIfNeeded() {
        val cities = dao.getAllCities().first()

        // Check if the cities list is empty
        if (cities.isEmpty()) {
            val defaultCities = listOf(
                PopularCity(name = "Стамбул", text = "Популярное назначение"),
                PopularCity(name = "Сочи", text = "Популярное назначение"),
                PopularCity(name = "Пхукет", text = "Популярное назначение")
            )
            dao.insertCities(defaultCities)
        }
    }
}
