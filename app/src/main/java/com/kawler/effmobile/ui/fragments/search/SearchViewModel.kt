package com.kawler.effmobile.ui.fragments.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawler.effmobile.data.repositories.PopularCityRepositoryImpl
import com.kawler.effmobile.domain.models.PopularCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PopularCityRepositoryImpl
) : ViewModel() {
    private val _cities = MutableStateFlow<List<PopularCity>>(emptyList())
    val cities: StateFlow<List<PopularCity>> = _cities

    init {
        viewModelScope.launch {
            repository.checkAndInsertCitiesIfNeeded()
            repository.getAllCities().collect {
                _cities.value = it
            }
        }
    }
}