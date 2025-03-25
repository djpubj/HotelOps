package com.test.hotelops.ui.presentation.authentication

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.hotelops.navigation.create
import com.test.hotelops.roomdatabase.entity.Hotel


@Composable
fun CreateHotelUI(navController: NavController) {

    val viewModel = viewModel(modelClass = HotelViewModel::class.java)
    var hotelname by remember { mutableStateOf("") }
    var numberofroom by remember { mutableStateOf("") }
    val context = LocalContext.current // Get the context here

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Create Hotel", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = hotelname,
            onValueChange = { hotelname = it },
            label = { Text(text = "Hotel Name") }
        )


        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            Toast.makeText(context, "Hotel created", Toast.LENGTH_SHORT).show()
            viewModel.insertHotel(Hotel(hotelname = hotelname))
            navController.navigate(create)

        }) {
            Text(text = "Create Hotel")
        }


        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { navController.navigate(create) }) {
            Text(text = "I have an Hotel")
        }

    }
}
