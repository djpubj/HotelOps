package com.test.hotelops.ui.presentation.booking

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.hotelops.roomdatabase.entity.Booking
import com.test.hotelops.roomdatabase.entity.Customer
import com.test.hotelops.roomdatabase.entity.Money
import com.test.hotelops.ui.presentation.authentication.AuthViewmodel
import com.test.hotelops.ui.presentation.previouscustomer.CustomerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CreateBookingUI(navController: NavController, userId: String) {
    val viewModel = viewModel(modelClass = BookingViewModel::class.java)
    val moneyviewModel = viewModel(modelClass = MoneyViewModel::class.java)
    val AuthviewModel = viewModel(modelClass = AuthViewmodel::class.java)
    val CustomerViewModel = viewModel(modelClass = CustomerViewModel::class.java)
    val context = LocalContext.current
    var roomNumber by remember { mutableStateOf("") }
    var customerName by remember { mutableStateOf("") }
    var emailId by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var payment by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    val isFormValid = customerName.isNotEmpty() &&
            emailId.isNotEmpty() &&
            contactNumber.isNotEmpty() &&
            address.isNotEmpty() &&
            roomNumber.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Book a Room", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = customerName,
            onValueChange = { customerName = it },
            label = { Text("Customer Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = emailId,
            onValueChange = { emailId = it },
            label = { Text("Customer Email ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = contactNumber,
            onValueChange = { contactNumber = it },
            label = { Text("Customer Contact Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Customer Address") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = roomNumber,
            onValueChange = { roomNumber = it },
            label = { Text("Customer Alloted Room Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = payment,
            onValueChange = { payment = it },
            label = { Text("Total payment done by customer") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                coroutineScope.launch {
                    if (isFormValid) {
                        AuthviewModel.getUserByonlyId(userId)
                        delay(2000)
                        if (AuthviewModel.fetchuser.value != null) {
                            val newcustomer = Customer(
                                customername = customerName,
                                hotelId =AuthviewModel.fetchuser.value!!.hotelId ,
                                emailId = emailId,
                                contactnumber = contactNumber,
                                address = address
                            )
                            val newBooking = Booking(
                                userId = userId,
                                customerId = newcustomer.customerId,
                                roomnumber = roomNumber,
                                hotelId = AuthviewModel.fetchuser.value!!.hotelId,
                                payment = payment.toInt()
                            )
                            CustomerViewModel.insertCustomer(newcustomer)
                            viewModel.insertBooking(newBooking)
                            moneyviewModel.insertMoney(Money(bookingId = newBooking.bookingId, hotelId = newBooking.hotelId, payment = payment.toInt()))
                            Toast.makeText(context, "Booking Created!", Toast.LENGTH_SHORT).show()
                            navController.navigateUp()
                        } else {
                            Toast.makeText(context, "Error in Booking Retry!", Toast.LENGTH_SHORT)
                                .show()
                        }
                    } else {
                        Toast.makeText(context, "Fill all fields!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit Booking")
        }
    }
}


