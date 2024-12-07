package com.kawler.effmobile.ui.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kawler.effmobile.domain.models.Ticket
import com.kawler.effmobile.ui.delegates.TicketDetailsDelegate

class TicketsDetailsRVAdapter : ListDelegationAdapter<List<Ticket>>() {

    init {
        delegatesManager.addDelegate(TicketDetailsDelegate())
    }

    fun updateTickets(newTickets: List<Ticket>) {
        items = newTickets
        notifyDataSetChanged()
    }
}