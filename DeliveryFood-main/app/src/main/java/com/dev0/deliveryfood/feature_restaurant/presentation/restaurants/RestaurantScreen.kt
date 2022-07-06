package com.dev0.deliveryfood.feature_restaurant.presentation.restaurants

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev0.deliveryfood.R
import com.dev0.deliveryfood.ui.theme.DeliveryFoodTheme


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestaurantScreen(

) {
    val list = listOf("El gavilan", "la Negrita", "Puro Limon", "Tanta")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        TextField(
            value = "Buscar restaurantes",
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
            //colors = TextFieldDefaults.textFieldColors()
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))
        /*Text(
            text = "PANTALLA }",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )*/
        /*LazyVerticalGrid(
            cells = GridCells.Adaptive(128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(list.size) { index ->
                    ItemRestaurant(item = list[index])
                }
            }
        )*/
        LazyColumn(
            contentPadding = PaddingValues(
                top = 12.dp,
                bottom = 100.dp
            ),

        ) {
            items(list.size) { index ->
                ItemRestaurant(item = list[index])
            }
        }

    }
}

@Composable
fun ItemRestaurant(
    item: String
){
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        CardContent(name = item)
    /*Text(
            text = item,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            color = Color(0xFFFFFFFF),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )*/
    }
}

@Composable
fun CardContent(name: String) {
    var expanded by remember { mutableStateOf(false) }

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


                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        modifier = Modifier.padding(8.dp).size(64.dp),
                        painter = painterResource(id = R.drawable.gavlan),
                        contentDescription = "",
                    )

                    Column() {
                        Text(text = "Cercado")
                        Text(
                            text = name,
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }


                }

                if (expanded) {

                    Image(
                        modifier = Modifier
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(128.dp),
                        painter = painterResource(id = R.drawable.negrita),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )


                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = ("Consumo en el lugar·\n" +
                                    "Retiros en la puerta·\n" +
                                    "Entrega sin contacto")
                        )
                        Button(
                            onClick = { /*TODO*/ }
                        ) {
                            Text(text = "Ver platos")
                        }
                    }
                }
            }

        }
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Filled.KeyboardArrowDown else Filled.ArrowDropDown,
                contentDescription = ""
            )
        }
    }

}

@Preview
@Composable
fun Preview(){
    DeliveryFoodTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            RestaurantScreen()
        }
    }
}