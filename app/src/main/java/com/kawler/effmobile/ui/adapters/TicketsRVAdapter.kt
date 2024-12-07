package com.kawler.effmobile.ui.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kawler.effmobile.R
import com.kawler.effmobile.domain.models.TicketsOffer
import com.kawler.effmobile.ui.delegates.TicketsOfferDelegate

class TicketsRVAdapter : ListDelegationAdapter<List<TicketsOffer>>() {

    init {
        delegatesManager.addDelegate(
            TicketsOfferDelegate(
                tintList = listOf(R.color.red, R.color.blue, R.color.white)
            )
        )
        items = emptyList()
    }

    fun setTickets(newTickets: List<TicketsOffer>) {
        items = newTickets
        notifyDataSetChanged()
    }
}