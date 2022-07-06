package com.dev0.deliveryfood.feature_food.presentation.foods

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_food.domain.repository.FoodRepository
import com.dev0.deliveryfood.feature_food.presentation.foods.FoodsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class FoodsViewModel() {

    private val repository =  FoodRepository()
    private val _state = mutableStateOf(FoodsState())
    val state: State<FoodsState> = _state

    init {
        getFoods()
    }


    private fun getFoods() {
        val fo = Food(id = 1, name = "Papa con queso",
            description = "Lava, pela y pon a hervir las patatas. Una vez estén cocidas tritúralas hasta formar un puré. A continuación forma bolas de patatas con queso en el centro. Puedes utilizar queso rallado o troceado, te recomiendo utilizar un queso de sabor ligeramente salado", restaurant = 1, qualification = 1.5f,
            image = "https://scontent-lim1-1.xx.fbcdn.net/v/t1.6435-9/119229450_2369367750038836_3946458816573015074_n.jpg?stp=dst-jpg_p526x296&_nc_cat=103&ccb=1-7&_nc_sid=8bfeb9&_nc_ohc=jJKsk_jGGawAX__nPj6&_nc_ht=scontent-lim1-1.xx&oh=00_AT-r5dcgkGFeakMZbz1QKbIHvQiSsoIeewwMggBFoOq9ag&oe=62B477D2")
        val f = mutableListOf(fo)
        val foods = f
        _state.value = state.value.copy(
            foods = foods
        )
    }
}