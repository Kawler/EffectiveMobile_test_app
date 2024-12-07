package com.kawler.effmobile.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kawler.effmobile.domain.models.Offer
import com.kawler.effmobile.domain.models.PopularCity
import com.kawler.effmobile.domain.models.Ticket
import com.kawler.effmobile.domain.models.TicketsOffer
import com.kawler.effmobile.domain.utils.DatabaseConverters

@Database(
    entities = [PopularCity::class, Offer::class, TicketsOffer::class, Ticket::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DatabaseConverters::class)
abstract class EffMobileDatabase : RoomDatabase() {
    abstract fun popularCityDao(): PopularCityDao
    abstract fun offerDao(): OfferDao
    abstract fun ticketsOfferDao(): TicketsOfferDao
    abstract fun ticketsDao(): TicketsDao
}