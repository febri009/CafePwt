package com.example.cafepwt.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafepwt.data.CafeList
import com.example.cafepwt.data.CafeRepository
import com.example.cafepwt.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: CafeRepository,
) : ViewModel() {

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _uiState: MutableStateFlow<UiState<List<CafeList>>> = MutableStateFlow(UiState.Loading)

    val uiState: StateFlow<UiState<List<CafeList>>>
        get() = _uiState

    fun getAllCafe() {
        viewModelScope.launch {
            repository.getAllCafe()
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message.toString())
                }
                .collect { dataCafe ->
                    _uiState.value = UiState.Success(dataCafe)
                }
        }
    }

    fun searchCafe(newQuery: String) {
        viewModelScope.launch {
            _query.value = newQuery
            repository.searchCafe(_query.value)
                .catch { exception ->
                    _uiState.value = UiState.Error(exception.message.toString())
                }
                .collect { cafe ->
                    _uiState.value = UiState.Success(cafe)
                }
        }
    }
}



