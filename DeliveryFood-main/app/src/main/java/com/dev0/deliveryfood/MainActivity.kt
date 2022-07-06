package com.dev0.deliveryfood


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dev0.deliveryfood.core.presentation.MainScreen
import com.dev0.deliveryfood.ui.theme.DeliveryFoodTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.messaging.RemoteMessage
class MainActivity : ComponentActivity() {

   // FirebaseFirestone firestore;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            /*DeliveryFoodTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }*/
            //val db = Firebase.firestore;
           // firestore= FirebaseFirestore.getInstance();
            MainScreen()
        }
    }
}
