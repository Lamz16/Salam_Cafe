package com.lamz.salamcafe.ui.screen.order

import com.lamz.salamcafe.model.OrderMenu

data class OrderState(
    val orderState : List<OrderMenu>,
    val totalPrice : Int
)
