package com.lamz.salamcafe.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lamz.salamcafe.data.DataRepository
import com.lamz.salamcafe.model.OrderMenu
import com.lamz.salamcafe.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewmodel(private val repository: DataRepository) : ViewModel() {

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
    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun searchCoffeMenu(query: String) {
        viewModelScope.launch {
            _query.value = query
            repository.getCoffeMenuByQuery(query)
                .catch { e ->
                    _uiState.value = UiState.Error(e.message.toString())
                }
                .collect { result ->
                    _uiState.value = UiState.Success(result)
                }
        }
    }


}