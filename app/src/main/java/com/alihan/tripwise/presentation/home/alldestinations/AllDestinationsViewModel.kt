package com.alihan.tripwise.presentation.home.alldestinations

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
        }
    }

    fun loadNextPage() {
        if (_uiState.value.isLoadingMore || !_uiState.value.hasMore || _uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingMore = true)
            val newItems = getAllDestinationsUseCase(limit = pageSize, offset = currentOffset)
            if (newItems.isNotEmpty()) {
                currentOffset += newItems.size
                val updatedList = _uiState.value.destinations + newItems
                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    destinations = updatedList,
                    hasMore = newItems.size >= pageSize
                )
            } else {
                _uiState.value = _uiState.value.copy(
                    isLoadingMore = false,
                    hasMore = false
                )
            }
        }
    }
}
