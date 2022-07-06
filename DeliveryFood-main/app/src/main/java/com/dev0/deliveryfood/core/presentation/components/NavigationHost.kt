package com.dev0.deliveryfood.core.presentation.components

import FoodDetailScreen
import PantallaPrueba
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import com.dev0.deliveryfood.feacture_cart.presentation.cart.CartScreen
import com.dev0.deliveryfood.feature_food.presentation.foods.FoodScreen
import com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.RestaurantScreen

@Composable
fun NavigationHost(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Destinations.Restaurants.route) {
        composable( Destinations.Restaurants.route ) {
            RestaurantScreen()
        }
        composable( Destinations.Foods.route ) {
            FoodScreen(navController)
        }
        composable(
            Destinations.FoodDetail.route,
            arguments = listOf(navArgument("foodId"){
            type = NavType.IntType
        })) {
            FoodDetailScreen( navController, it.arguments?.getInt("foodId") )
        }
        composable( Destinations.Pantalla1.route ) {
            PantallaPrueba( title = "Su pedido se realizo con exito" )
        }
        composable( Destinations.Cart.route ) {
            CartScreen( title = "Aqui se paga" )
        }
    }
}