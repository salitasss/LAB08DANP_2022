import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.R
import com.dev0.deliveryfood.feature_food.presentation.food_detail.FoodDetailState
import com.dev0.deliveryfood.feature_food.presentation.food_detail.FoodDetailViewModel

@Composable
fun FoodDetailScreen(
    navController: NavHostController,
    foodId: Int?,
    viewModel: FoodDetailViewModel = FoodDetailViewModel()
) {

    if (foodId != null) viewModel.getFoodById(id = foodId)
    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        FoodCardDetail(state)
    }
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun FoodCardDetail(foodDetail: FoodDetailState){

    var cant by remember { mutableStateOf(0) }

    Card(

    ) {
        // Contenedor
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Miniatura
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.zingaro),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(Modifier.fillMaxWidth()) {
                    Text(text = foodDetail.food!!.name, style = MaterialTheme.typography.h6)
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = foodDetail.restaurant!!, style = MaterialTheme.typography.body1)
                    }
                }
            }

            Image(
                painter = rememberImagePainter(data = foodDetail.food!!.image),
                contentDescription = "",
                Modifier
                    .background(color = Color.LightGray)
                    .fillMaxWidth()
                    .height(194.dp)
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                Text(
                    text = foodDetail.food!!.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))


                Box(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {

                    // Botones
                    Row(modifier = Modifier.align(Alignment.CenterStart)) {

                        TextButton(onClick = { if(cant > 0) cant-- }) {
                            Text(text = "-")
                        }
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.secondary
                            )
                        ){
                            Text(text = cant.toString())
                        }

                        TextButton(onClick = { cant++ }) {
                            Text(text = "+")
                        }

                    }

                    // Iconos
                    Row(modifier = Modifier.align(Alignment.TopEnd)) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Favorite, contentDescription = null)
                        }

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Default.Share, contentDescription = null)
                        }
                    }

                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center) {
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Agregar al carrito")
                }
            }
        }
    }
}