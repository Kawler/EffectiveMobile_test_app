package com.kawler.effmobile.ui.fragments.main_tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawler.effmobile.data.repositories.OfferRepositoryImpl
import com.kawler.effmobile.domain.models.Offer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTicketsViewModel @Inject constructor(
    private val repository: OfferRepositoryImpl
) : ViewModel() {
    private val _offers = MutableStateFlow<List<Offer>>(emptyList())
    val offers: StateFlow<List<Offer>> = _offers

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            repository.refreshOffers()
            repository.getAllOffers().collect {
                _offers.value = it
            }
        }
    }
}