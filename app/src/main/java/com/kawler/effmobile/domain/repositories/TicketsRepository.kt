package com.kawler.effmobile.domain.repositories

import com.kawler.effmobile.domain.models.Ticket
import kotlinx.coroutines.flow.Flow

interface TicketsRepository {
    suspend fun fetchTickets(): Flow<List<Ticket>>
}