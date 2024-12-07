package com.kawler.effmobile.domain.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

data class TicketsResponse(
    val tickets: List<Ticket>
)

@Entity
data class Ticket(
    @PrimaryKey val id: Int,
    val badge: String?,
    @Embedded val price: Price,
    val provider_name: String,
    val company: String,
    @Embedded(prefix = "departure_") val departure: Departure,
    @Embedded(prefix = "arrival_") val arrival: Arrival,
    val has_transfer: Boolean,
    val has_visa_transfer: Boolean,
    @Embedded(prefix = "luggage_") val luggage: Luggage,
    @Embedded(prefix = "hand_luggage_") val hand_luggage: HandLuggage,
    val is_returnable: Boolean,
    val is_exchangable: Boolean
)

data class Departure(
    val town: String,
    val date: String,
    val airport: String
)

data class Arrival(
    val town: String,
    val date: String,
    val airport: String
)

data class Luggage(
    val has_luggage: Boolean,
    @Embedded(prefix = "price_") val price: Price?
)

data class HandLuggage(
    val has_hand_luggage: Boolean,
    val size: String?
)