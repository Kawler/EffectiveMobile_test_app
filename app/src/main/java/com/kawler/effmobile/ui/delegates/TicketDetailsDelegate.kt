package com.kawler.effmobile.ui.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.kawler.effmobile.R
import com.kawler.effmobile.databinding.RvFlightDetailsItemBinding
import com.kawler.effmobile.domain.models.Ticket
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

class TicketDetailsDelegate : AdapterDelegate<List<Ticket>>() {

    override fun isForViewType(items: List<Ticket>, position: Int): Boolean {
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RvFlightDetailsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TicketViewHolder(binding)
    }

    override fun onBindViewHolder(
        items: List<Ticket>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        val ticket = items[position]
        val binding = (holder as TicketViewHolder).binding

        with(binding) {
            val departureTime = formatTime(ticket.departure.date)
            val arrivalTime = formatTime(ticket.arrival.date)
            rvFlightDetailsItemTime.text = "$departureTime — $arrivalTime "

            val flightDuration = calculateDuration(ticket.departure.date, ticket.arrival.date)
            var detailsText = String.format("%.1f", flightDuration) + "ч в пути"
            if (!ticket.has_transfer) {
                detailsText += " / Без пересадок"
            }
            rvFlightDetailsItemDetails.text = detailsText

            rvFlightDetailsItemPrice.text = "${formatPrice(ticket.price.value)} ₽ "
            rvFlightDetailsItemDeparture.text = ticket.departure.airport + " "
            rvFlightDetailsItemArrival.text = ticket.arrival.airport + " "

            if (ticket.badge != null) {
                rvFlightDetailsItemTag.visibility = View.VISIBLE
                rvFlightDetailsItemTagText.text = ticket.badge
            } else {
                rvFlightDetailsItemTag.visibility = View.GONE
            }
            redCircle.setImageResource(R.drawable.ic_company)
        }
    }

    private fun formatPrice(value: Int): String {
        return String.format("%,d", value).replace(",", " ")
    }

    private fun formatTime(dateTime: String): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm", Locale.getDefault())
        val zonedDateTime = LocalDateTime.parse(dateTime).atZone(ZoneOffset.UTC)
        return formatter.format(zonedDateTime)
    }

    private fun calculateDuration(departure: String, arrival: String): Double {
        val departureTime = LocalDateTime.parse(departure).atZone(ZoneOffset.UTC)
        val arrivalTime = LocalDateTime.parse(arrival).atZone(ZoneOffset.UTC)
        val duration = Duration.between(departureTime, arrivalTime)
        val hours = duration.toHours()
        val minutes = duration.toMinutes() % 60
        return hours + minutes / 60.0
    }

    inner class TicketViewHolder(val binding: RvFlightDetailsItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}
