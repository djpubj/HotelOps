package com.test.hotelops.ui.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.hotelops.navigation.allcustomer
import com.test.hotelops.navigation.createbooking
import com.test.hotelops.navigation.login
import com.test.hotelops.roomdatabase.entity.Booking
import com.test.hotelops.ui.presentation.booking.BookingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(navController: NavController, userId: String) {
    val BookingviewModel = viewModel(modelClass = BookingViewModel::class.java)
    val bookings by BookingviewModel.bookingById.observeAsState(emptyList<Booking>())
    LaunchedEffect(userId) {
        BookingviewModel.getBookingById(userId)
    }

    Scaffold(topBar = {
        TopAppBar(modifier = Modifier.shadow(8.dp), title = { Text("HotelOps") },actions = {
            Button(onClick = {
                navController.navigate(login)
            }) {
                Text("SignOut")
            }
        })

    }, floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(createbooking(userId = userId)) }
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add")
        }
    }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(Color.LightGray.copy(alpha = 0.4f))
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Button(
                onClick = { navController.navigate(allcustomer(userId = userId)) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary // Primary theme color
                ),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 6.dp),
                shape = MaterialTheme.shapes.medium, // Rounded corners
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = "View All Customers",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(bookings.reversed()) { item ->
                    bookingItem(item)
                }
            }

        }
    }
}


@Composable
fun bookingItem(booking: Booking) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "Room number: ${booking.roomnumber}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "bookingId: ${booking.bookingId}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "CustomerId: ${booking.customerId}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

//GuestCard(
//name = "Mr. Abcd",
//reservationId = "OCA65678A4AED",
//folio = "12...",
//stayInfo = "3 Night Stay",
//roomType = "Fam suite / 1111",
//guests = "2 Adult | 1 Child",
//status = "Arrived",
//total = "$634725",
//balance = "$634725",
//statusColor = Color(0xFFFFA000)
//)
//GuestCard(
//name = "Mr. Cristian Maldonado",
//reservationId = "XCD0E67928D90",
//folio = "1315",
//stayInfo = "2 Night Stay",
//roomType = "STD / 7777",
//guests = "2 Adult | 1 Child",
//status = "Stayover",
//total = "$587",
//balance = "$0",
//statusColor = Color(0xFF388E3C)
//)
//

//val stalist = listOf(
//    StatisticCard("Total Revenue", "$159,923", "$57,982", "-63.74%", Color.Red),
//    StatisticCard(
//        "Avg. Daily Rate", "$5,077", "$1,870", "-63.17%", Color.Red
//    ),
//    StatisticCard("Booking Lead Time", "-8 Days", "-8 Days", "0%", Color.Blue),
//    StatisticCard("Avg. Length of Stay", "8 Nights", "9 Nights", "12.5%", Color.Green),
//    StatisticCard("Total Payment", "$1,000,000,000,709,383", "$1,491", "-100%", Color.Red),
//    StatisticCard("RevPar", "$579", "$211", "-63.56%", Color.Red)
//)


//            LazyVerticalGrid(
//                state = rememberLazyGridState(),
//                columns = GridCells.Fixed(2),
//                modifier = Modifier
//                    .fillMaxHeight()
//                    .weight(1f), // Let the grid take up remaining space
//                contentPadding = PaddingValues(8.dp),
//                verticalArrangement = Arrangement.spacedBy(8.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//
//                items(stalist) { item ->
//                    Box(
//                        modifier = Modifier
//                            .aspectRatio(1f), // Ensures square shape for equal sizing
//                        contentAlignment = Alignment.Center
//                    ) {
//                        StatisticCar(
//                            item.title,
//                            item.value,
//                            item.change,
//                            item.percentage,
//                            item.color
//                        )
//
//                    }
//                }
//            }