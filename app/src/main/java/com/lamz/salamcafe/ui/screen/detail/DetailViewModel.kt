package com.lamz.salamcafe.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamz.salamcafe.data.DataRepository
import com.lamz.salamcafe.model.CafeShop
import com.lamz.salamcafe.model.OrderMenu
import com.lamz.salamcafe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: DataRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMenu>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMenu>>
        get() = _uiState

    fun getMenuById(menuId : Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getCoffeMenuById(menuId))
        }
    }

    fun addToChart(cafeShop: CafeShop, count: Int){
        viewModelScope.launch {
            repository.orderMenu(cafeShop.id, count)
        }
    }

}