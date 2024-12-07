package com.kawler.effmobile.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kawler.effmobile.domain.models.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(ticket: Ticket)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tickets: List<Ticket>)

    @Query("SELECT * FROM Ticket WHERE id = :ticketId")
    suspend fun getTicketById(ticketId: Int): Ticket?

    @Query("SELECT * FROM Ticket")
    suspend fun getAllTickets(): List<Ticket>

    @Query("SELECT * FROM Ticket")
    fun getAllTicketsFlow(): Flow<List<Ticket>>

    @Delete
    suspend fun delete(ticket: Ticket)

    @Query("DELETE FROM Ticket")
    suspend fun deleteAll()
}