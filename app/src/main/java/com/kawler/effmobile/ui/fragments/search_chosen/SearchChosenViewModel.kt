package com.kawler.effmobile.ui.fragments.search_chosen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawler.effmobile.data.repositories.TicketsOfferRepositoryImpl
import com.kawler.effmobile.domain.models.TicketsOffer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchChosenViewModel @Inject constructor(
    private val repository: TicketsOfferRepositoryImpl
) : ViewModel() {
    private val _ticketsOffers = MutableStateFlow<List<TicketsOffer>>(emptyList())
    val ticketsOffers: StateFlow<List<TicketsOffer>> = _ticketsOffers
    private val _selectedDate = MutableLiveData<String>()
    val selectedDate: LiveData<String> get() = _selectedDate

    fun setSelectedDate(date: String) {
        _selectedDate.value = date
    }

    init {
        loadTicketsOffers()
    }

    private fun loadTicketsOffers() {
        viewModelScope.launch {
            repository.refreshTicketsOffers()
            repository.getAllTicketsOffers().collect {
                _ticketsOffers.value = it
            }
        }
    }

}