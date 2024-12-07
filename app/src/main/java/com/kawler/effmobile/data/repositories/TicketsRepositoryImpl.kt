package com.kawler.effmobile.data.repositories

import com.kawler.effmobile.data.db.TicketsDao
import com.kawler.effmobile.domain.models.Ticket
import com.kawler.effmobile.domain.remote.MockApiService
import com.kawler.effmobile.domain.repositories.TicketsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TicketsRepositoryImpl @Inject constructor(
    private val ticketDao: TicketsDao,
    private val apiService: MockApiService
) : TicketsRepository {
    override suspend fun fetchTickets(): Flow<List<Ticket>> {
        return try {
            val apiResponse = apiService.getThirdResponse()
            val apiCount = apiResponse.tickets.size

            val localCount = ticketDao.getAllTickets().size
            if (localCount == apiCount) {
                ticketDao.getAllTicketsFlow()
            } else {
                val tickets = apiResponse.tickets
                ticketDao.deleteAll()
                ticketDao.insertAll(tickets)
                ticketDao.getAllTicketsFlow()
            }
        } catch (e: Exception) {
            ticketDao.getAllTicketsFlow()
        }
    }
}