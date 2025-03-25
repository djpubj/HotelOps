package com.test.hotelops.ui.presentation.authentication

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.hotelops.navigation.createhotel
import com.test.hotelops.navigation.home
import com.test.hotelops.navigation.login
import com.test.hotelops.roomdatabase.entity.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun Create(navController: NavController) {

    val viewModel = viewModel(modelClass = AuthViewmodel::class.java)
    var hotelId by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current // Get the context here
    val hotelviewModel = viewModel(modelClass = HotelViewModel::class.java)
    val coroutineScope = rememberCoroutineScope()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create Account", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hotelId,
            onValueChange = { hotelId = it },
            label = { Text(text = "Hotel Id") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email Id") }
        )

        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "User Name") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            coroutineScope.launch {
                hotelviewModel.checkHotel(hotelId)
                Log.e("hotel ", "status ${hotelviewModel.hotelExists.value}")
                delay(2000)
                Log.e("hotel ", "status ${hotelviewModel.hotelExists.value}")

                if (hotelviewModel.hotelExists.value == true) {
                    val newuser = User(
                        hotelId = hotelId,
                        username = name,
                        emailId = email,
                        userpassword = password
                    )
                    viewModel.adduser(newuser)
                    Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                    navController.navigate(home(userId =newuser.userId))
                } else {
                    Toast.makeText(context, "Hotel do not exists", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text(text = "Create")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.navigate(login) }) {
            Text(text = "I have an account")
        }
        Spacer(modifier = Modifier.height(2.dp))
        TextButton(onClick = { navController.navigate(createhotel) }) {
            Text(text = "I don't have Hotel")
        }
    }
}
