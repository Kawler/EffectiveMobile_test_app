package com.kawler.effmobile.ui.adapters

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.kawler.effmobile.R
import com.kawler.effmobile.domain.models.PopularCity
import com.kawler.effmobile.ui.delegates.CitySuggestionsDelegate

class CitySuggestionsAdapter : ListDelegationAdapter<List<PopularCity>>() {

    init {
        delegatesManager.addDelegate(
            CitySuggestionsDelegate(
                listOf(
                    R.drawable.resource_1,
                    R.drawable.resource_10,
                    R.drawable.resource_2
                )
            )
        )
    }

    fun updateData(data: List<PopularCity>) {
        items = data
        notifyDataSetChanged()
    }
}

