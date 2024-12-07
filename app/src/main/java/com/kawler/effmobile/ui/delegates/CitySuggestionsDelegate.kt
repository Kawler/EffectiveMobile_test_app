package com.kawler.effmobile.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.kawler.effmobile.databinding.RvSearchCitiesBinding
import com.kawler.effmobile.domain.models.PopularCity

class CitySuggestionsDelegate(private val images: List<Int>) :
    AdapterDelegate<List<PopularCity>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = RvSearchCitiesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CityViewHolder(binding)
    }

    override fun isForViewType(items: List<PopularCity>, position: Int): Boolean {
        return true
    }

    override fun onBindViewHolder(
        items: List<PopularCity>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: List<Any>
    ) {
        val city = items[position]
        (holder as CityViewHolder).bind(city, position)
    }

    inner class CityViewHolder(private val binding: RvSearchCitiesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(city: PopularCity, adapterPosition: Int) {
            binding.rvSearchCitiesName.text = city.name
            binding.rvSearchCitiesDescription.text = city.text
            binding.rvSearchCitiesImg.setImageResource(images[adapterPosition % images.size])
        }
    }
}
