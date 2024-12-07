package com.kawler.effmobile.ui.delegates

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.kawler.effmobile.databinding.RvSearchChosenFlightBinding
import com.kawler.effmobile.domain.models.TicketsOffer

class TicketsOfferDelegate(
    private val tintList: List<Int>
) : AdapterDelegate<List<TicketsOffer>>() {

    override fun isForViewType(items: List<TicketsOffer>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RvSearchChosenFlightBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<TicketsOffer>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        val ticket = items[position]
        (holder as TicketViewHolder).bind(ticket, position, tintList)
    }

    private class TicketViewHolder(
        private val binding: RvSearchChosenFlightBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(ticket: TicketsOffer, position: Int, tintList: List<Int>) {
            binding.rvSearchChosenCompany.text = ticket.title
            binding.rvSearchChosenPrice.text = "${formatPrice(ticket.price.value)} ₽ "
            binding.rvSearchChosenTime.text = ticket.time_range.joinToString("   ")

            val colorRes = tintList[position % tintList.size]
            binding.rvSearchChosenImg.imageTintList = ColorStateList.valueOf(
                binding.root.context.getColor(colorRes)
            )
        }

        private fun formatPrice(value: Int): String {
            return String.format("%,d", value).replace(",", " ")
        }
    }
}
