package com.kawler.effmobile.domain.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class OffersTicketsResponse(
    val tickets_offers: List<TicketsOffer>
)

@Entity(tableName = "tickets_offers")
data class TicketsOffer(
    @PrimaryKey val id: Int,
    val title: String,
    val time_range: List<String>,
    @Embedded val price: Price
)