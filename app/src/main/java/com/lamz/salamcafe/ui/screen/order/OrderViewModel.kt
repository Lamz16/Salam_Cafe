package com.lamz.salamcafe.ui.screen.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamz.salamcafe.data.DataRepository
import com.lamz.salamcafe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: DataRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderState>>
        get() = _uiState

    fun orderMenu(menuId : Long, count : Int){
        viewModelScope.launch {
            repository.orderMenu(menuId, count)
                .collect{ isUpdated ->
                    if (isUpdated){
                        getAddedOrderMenu()
                    }

                }
        }
    }

    fun getAddedOrderMenu() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderMenu()
                .collect{ orderMenu ->
                    val totalPrice = orderMenu.sumOf { it.cafeShop.price * it.count }
                        _uiState.value = UiState.Success(OrderState(orderMenu, totalPrice))
                }
        }
    }

}