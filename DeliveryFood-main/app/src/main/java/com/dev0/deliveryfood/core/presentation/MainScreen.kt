package com.dev0.deliveryfood.core.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dev0.deliveryfood.core.presentation.components.BottomNavigationBar
import com.dev0.deliveryfood.core.presentation.components.NavigationHost
import com.dev0.deliveryfood.core.presentation.utils.Destinations.*
import currentRoute

@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val scope = rememberCoroutineScope()

    val navigateItems = listOf(
        Restaurants,
        Foods,
        Pantalla1,
    )

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val floatingButtonState = rememberSaveable { (mutableStateOf(true)) }

    when (currentRoute(navController = navController)) {
        Restaurants.route -> bottomBarState.value = true
        Foods.route -> bottomBarState.value = true
        Pantalla1.route -> bottomBarState.value = true


        Cart.route -> {
            bottomBarState.value = false
        }
        else -> {
            bottomBarState.value = false
        }

    }
    when (currentRoute(navController = navController)) {
        Cart.route -> {
            floatingButtonState.value = false
        }
        else -> {
            floatingButtonState.value = true
        }

    }

    Scaffold(
        scaffoldState = scaffoldState,
        //topBar = { TopBar(scope = scope, scaffoldState = scaffoldState ) },
        bottomBar = { BottomNavigationBar(navController = navController, items = navigateItems, bottomBarState = bottomBarState) },
        /*drawerContent = { Drawer(
            scope = scope,
            scaffoldState = scaffoldState,
            navController = navController,
            items = navigateItems
        ) }*/
        floatingActionButton = {
            if (currentRoute(navController = navController) != Cart.route) {
                CartFloatingActionButton(navController = navController, floatingButtonState = floatingButtonState)
            }
        }
    ) {
        NavigationHost(navController)
    }

}

@Composable
fun CartFloatingActionButton(
    navController: NavHostController,
    floatingButtonState: MutableState<Boolean>
) {

    AnimatedVisibility(
        visible = floatingButtonState.value,
    ){
        FloatingActionButton(
            onClick = {
                navController.navigate(Cart.route)
            },
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "")
        }
    }

}