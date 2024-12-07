package com.kawler.effmobile.ui.fragments.alltickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawler.effmobile.data.repositories.TicketsRepositoryImpl
import com.kawler.effmobile.domain.models.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllTicketsViewModel @Inject constructor(
    private val repository: TicketsRepositoryImpl
) : ViewModel() {
    private val _tickets = MutableStateFlow<List<Ticket>>(emptyList())
    val tickets: StateFlow<List<Ticket>> = _tickets

    init {
        loadTickets()
    }

    private fun loadTickets() {
        viewModelScope.launch {
            repository.fetchTickets().collect {
                _tickets.value = it
            }
        }
    }
}