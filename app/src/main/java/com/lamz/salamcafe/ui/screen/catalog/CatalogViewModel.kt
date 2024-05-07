package com.lamz.salamcafe.ui.screen.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamz.salamcafe.data.DataRepository
import com.lamz.salamcafe.model.OrderMenu
import com.lamz.salamcafe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CatalogViewModel(private val repository: DataRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState<List<OrderMenu>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMenu>>> get() = _uiState

    fun getAllCoffeMenu() {
        viewModelScope.launch {
            repository.getAllCoffeMenu()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderCoffe ->
                    _uiState.value = UiState.Success(orderCoffe)
                }
        }
    }
}