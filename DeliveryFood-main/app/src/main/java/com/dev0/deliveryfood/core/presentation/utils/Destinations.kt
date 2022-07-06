package com.dev0.deliveryfood.core.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Restaurants: Destinations("restaurants", "Restaurantes", Icons.Filled.Home)
    object Foods: Destinations("foods", "Comidas", Icons.Filled.Home)
    object Pantalla1: Destinations("pantalla1", "Pantalla 1", Icons.Filled.Home)
    object FoodDetail: Destinations("foodDetail/{foodId}", "Detalles de comida", Icons.Filled.Settings){
        fun createRoute(foodId: Int) = "foodDetail/$foodId"
    }
    object Cart: Destinations("cart", "Carro de compras", Icons.Filled.Favorite)
}