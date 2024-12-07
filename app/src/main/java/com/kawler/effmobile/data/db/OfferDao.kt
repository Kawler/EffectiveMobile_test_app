package com.kawler.effmobile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kawler.effmobile.domain.models.Offer
import kotlinx.coroutines.flow.Flow

@Dao
interface OfferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<Offer>)

    @Query("SELECT * FROM offers")
    fun getAllOffers(): Flow<List<Offer>>

    @Query("SELECT COUNT(*) FROM offers")
    suspend fun getAllOffersCount(): Int

    @Query("SELECT * FROM offers WHERE id = :id")
    suspend fun getOfferById(id: Int): Offer?

    @Query("DELETE FROM offers")
    suspend fun deleteAllOffers()
}