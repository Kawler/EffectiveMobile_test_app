package com.kawler.effmobile.ui.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kawler.effmobile.R
import com.kawler.effmobile.domain.models.Offer
import com.kawler.effmobile.ui.delegates.OfferDelegate

class OfferRVAdapter : ListDelegationAdapter<List<Offer>>() {

    init {
        delegatesManager.addDelegate(
            OfferDelegate(
                listOf(
                    R.drawable.resource_9,
                    R.drawable.resource_6,
                    R.drawable.resource_7
                )
            )
        )
    }

    fun updateData(data: List<Offer>) {
        items = data
        notifyDataSetChanged()
    }
}

