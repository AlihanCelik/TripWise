package com.alihan.tripwise.presentation.alldestinations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alihan.tripwise.di.model.Destination
import com.alihan.tripwise.domain.usecase.GetAllDestinationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AllDestinationsUiState(
    val destinations: List<Destination> = emptyList(),
    val filteredDestinations: List<Destination> = emptyList(),
    val searchQuery: String = "",
    val selectedCountry: String = "All",
    val selectedCity: String = "All",
    val selectedCategory: String = "All",
    val selectedSortOption: String = "Rating (High to Low)",
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = true,
    val error: String? = null
)

@HiltViewModel
class AllDestinationsViewModel @Inject constructor(
    private val getAllDestinationsUseCase: GetAllDestinationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AllDestinationsUiState())
    val uiState: StateFlow<AllDestinationsUiState> = _uiState.asStateFlow()

    private val pageSize = 10
    private var currentOffset = 0

    init {
        loadInitialDestinations()
    }

    fun loadInitialDestinations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val newItems = getAllDestinationsUseCase(limit = pageSize, offset = 0)
            currentOffset = newItems.size
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                destinations = newItems,
                hasMore = newItems.size >= pageSize
            )
            applyFilters()
        }
    }

    fun onSearchQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        applyFilters()
    }

    fun onCategorySelected(category: String) {
        _uiState.value = _uiState.value.copy(selectedCategory = category)
        applyFilters()
    }

    fun onApplyAdvancedFilters(country: String, city: String, category: String, sortOption: String) {
        _uiState.value = _uiState.value.copy(
            selectedCountry = country,
            selectedCity = city,
            selectedCategory = category,
            selectedSortOption = sortOption
        )
        applyFilters()
    }

    private fun applyFilters() {
        val state = _uiState.value
        val query = state.searchQuery.trim().lowercase()
        val country = state.selectedCountry.trim().lowercase()
        val city = state.selectedCity.trim().lowercase()
        val category = state.selectedCategory.trim().lowercase()

        var filtered = state.destinations.filter { destination ->
            val name = destination.name.lowercase()
            val address = destination.address.lowercase()

            val matchesQuery = query.isBlank() || name.contains(query) || address.contains(query)

            val matchesCountry = if (country == "all") true else address.contains(country) || name.contains(country)

            val matchesCity = if (city == "all") true else address.contains(city) || name.contains(city)

            val matchesCategory = if (category == "all" || category == "historical" || category == "nature" || category == "culture") {
                true
            } else {
                name.contains(category) || address.contains(category)
            }

            matchesQuery && matchesCountry && matchesCity && matchesCategory
        }

        filtered = if (state.selectedSortOption == "Name (A-Z)") {
            filtered.sortedBy { it.name }
        } else {
            filtered.sortedByDescending { it.rating }
        }

        _uiState.value = state.copy(filteredDestinations = filtered)

        if (filtered.isEmpty() && state.hasMore && !state.isLoading && !state.isLoadingMore) {
            loadNextPage()
        }
    }

    fun loadNextPage() {
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMore || _uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingMore = true)
            val newItems = getAllDestinationsUseCase(limit = pageSize, offset = currentOffset)
            if (newItems.isNotEmpty()) {
                currentOffset += newItems.size
                val updatedList = (_uiState.value.destinations + newItems)
                    .distinctBy { it.name.trim().lowercase() }
                    .distinctBy { it.id }

                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    destinations = updatedList,
                    hasMore = newItems.size >= pageSize
                )
                applyFilters()
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    hasMore = false
                )
            }
        }
    }
}
