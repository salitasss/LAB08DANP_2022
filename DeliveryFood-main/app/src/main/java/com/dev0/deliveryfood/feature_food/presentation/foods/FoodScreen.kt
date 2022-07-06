package com.dev0.deliveryfood.feature_food.presentation.foods

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.R
import com.dev0.deliveryfood.ui.theme.DeliveryFoodTheme
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import com.dev0.deliveryfood.feature_food.domain.model.Food


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FoodScreen(
    navController: NavHostController,
    viewModel: FoodsViewModel = FoodsViewModel()
) {
    val state = viewModel.state.value


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TextField(
            value = "Buscar comidas",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(),
            leadingIcon = {
                Icon(
                    imageVector = Filled.Search,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(12.dp)
                        .size(24.dp)
                )
            },
            trailingIcon = {

            },
            singleLine = true,
            shape = RectangleShape,
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        LazyVerticalGrid(
            cells = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 100.dp
            )
        ){
            items(state.foods.size) { index ->
                ItemFood(item = state.foods[index]) {
                    navController.navigate(Destinations.FoodDetail.createRoute(state.foods[index].id))
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFood(
    item: Food,
    onClick: () -> Unit
){
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        onClick = onClick
    ) {
        CardContent(food = item)
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardContent(food: Food) {

    Box(){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {


                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(bottom = 8.dp),
                    painter = rememberImagePainter(data = food.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = food.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(text = food.restaurant.toString(), style = MaterialTheme.typography.body1)
                }

                Text(
                    text = food.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                )


                Row() {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }
            }

        }
    }

}