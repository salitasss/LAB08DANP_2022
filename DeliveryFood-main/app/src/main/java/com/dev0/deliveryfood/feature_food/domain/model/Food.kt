package com.dev0.deliveryfood.feature_food.domain.model


data class Food (
    val name: String,
    val description: String,
    val restaurant : Int,
    val qualification : Float,
    val image : String,
    val id: Int
)