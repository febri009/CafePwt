package com.example.cafepwt.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafepwt.data.CafeList
import com.example.cafepwt.data.CafeRepository
import com.example.cafepwt.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCafeViewModel(
    private val repository: CafeRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CafeList>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<CafeList>>
        get() = _uiState

    fun getCafeById(cafeId: Long) {
        viewModelScope.launch {
            try {
                _uiState.value = UiState.Loading
                val cafe = repository.getCafeById(cafeId)
                _uiState.value = UiState.Success(cafe)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An error occurred")
            }
        }
    }
}