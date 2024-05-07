package com.lamz.salamcafe.data

import com.lamz.salamcafe.model.FakeCafeSource
import com.lamz.salamcafe.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class DataRepository {

    private val ordersMenu = mutableListOf<OrderMenu>()

    init{
        if (ordersMenu.isEmpty()){
            FakeCafeSource.dummyTopMenu.forEach {
                ordersMenu.add(OrderMenu(it, 0))
            }
        }
    }

    fun getAllCoffeMenu(): Flow<List<OrderMenu>> {
        return flowOf(ordersMenu)
    }

    fun getCoffeMenuById(coffeId : Long): OrderMenu {
        return ordersMenu.first { it.cafeShop.id == coffeId }
    }

    fun getCoffeMenuByQuery(query: String): Flow<List<OrderMenu>> {
        return flowOf(ordersMenu.filter { it.cafeShop.title.contains(query, ignoreCase = true) })
    }

    fun orderMenu(menuId: Long, newCountValue : Int): Flow<Boolean>{
        val index = ordersMenu.indexOfFirst { it.cafeShop.id == menuId }
        val result = if (index >= 0){
            val orderMenu = ordersMenu[index]
            ordersMenu[index] = orderMenu.copy(orderMenu.cafeShop , newCountValue)
            true
        }else{
            false
        }
        return  flowOf(result)
    }

    fun getAddedOrderMenu(): Flow<List<OrderMenu>>{
        return getAllCoffeMenu()
            .map { ordersMenu ->
                ordersMenu.filter { orderMenu ->
                    orderMenu.count != 0
                }
            }
    }

}