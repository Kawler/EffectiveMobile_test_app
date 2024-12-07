package com.kawler.effmobile.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.kawler.effmobile.databinding.RvMainTicketsBinding
import com.kawler.effmobile.domain.models.Offer

class OfferDelegate(private val imgList: List<Int>) : AdapterDelegate<List<Offer>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RvMainTicketsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OfferViewHolder(binding)
    }

    override fun isForViewType(items: List<Offer>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<Offer>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        val offer = items[position]
        (holder as OfferViewHolder).bind(offer, position, imgList)
    }

    inner class OfferViewHolder(private val binding: RvMainTicketsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: Offer, position: Int, imgList: List<Int>) {
            binding.rvMainTicketsImg.setImageResource(imgList[position % imgList.size])
            binding.rvMainTicketsName.text = offer.title
            binding.rvMainTicketsCity.text = offer.town
            binding.rvMainTicketsPrice.text = "от ${formatPrice(offer.price.value)} ₽"
        }

        private fun formatPrice(value: Int): String {
            return "%,d".format(value).replace(",", " ")
        }
    }
}
