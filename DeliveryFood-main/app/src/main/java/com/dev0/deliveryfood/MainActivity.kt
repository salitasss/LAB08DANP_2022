package com.dev0.deliveryfood


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import com.dev0.deliveryfood.core.presentation.MainScreen
import com.dev0.deliveryfood.ui.theme.DeliveryFoodTheme
import com.google.android.gms.tasks.OnCanceledListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.ktx.messaging

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
            FirebaseMessaging.getInstance().token
                .addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful){
                        Log.e("FCM Notify", "ERROR REGISTRANDO TOKEN", task.exception)
                        return@OnCompleteListener
                    }
                    val token: String? = task.result
                    Log.e("FCM Notify", token, task.exception)
                    Toast.makeText(this, token, Toast.LENGTH_SHORT).show()

                })
            Firebase.messaging.subscribeToTopic("foods")
                .addOnCompleteListener { task ->
                    var msg = "Subscribed"
                    if (!task.isSuccessful) {
                        msg = "Subscribe failed"
                    }
                    Log.d("MainActivity", msg)
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                }


        }
    }
}
