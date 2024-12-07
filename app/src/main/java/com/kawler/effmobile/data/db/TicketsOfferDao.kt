package com.kawler.effmobile.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kawler.effmobile.domain.models.TicketsOffer
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsOfferDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTicketsOffers(ticketsOffers: List<TicketsOffer>)

    @Query("SELECT * FROM tickets_offers")
    fun getAllTicketsOffers(): Flow<List<TicketsOffer>>

    @Query("SELECT COUNT(*) FROM tickets_offers")
    suspend fun getAllTicketsOffersCount(): Int

    @Query("DELETE FROM tickets_offers")
    suspend fun deleteAllTicketsOffers()
}