package com.lamz.salamcafe.model

data class CafeShop(
    val id : Long,
    val image : Int,
    val title: String,
    val price : Int,
    val isTop : Boolean? = false,
    val isBestSell : Boolean? = false
)
