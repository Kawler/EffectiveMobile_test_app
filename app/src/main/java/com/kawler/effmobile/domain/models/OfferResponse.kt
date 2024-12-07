package com.kawler.effmobile.domain.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class OfferResponse(
    val offers: List<Offer>
)

@Entity(tableName = "offers")
data class Offer(
    @PrimaryKey val id: Int,
    val title: String,
    val town: String,
    val img: Int? = null,
    @Embedded val price: Price
)

data class Price(
    val value: Int
)