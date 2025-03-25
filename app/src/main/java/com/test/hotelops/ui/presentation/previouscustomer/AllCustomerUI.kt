package com.test.hotelops.ui.presentation.previouscustomer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.test.hotelops.roomdatabase.entity.Customer
import com.test.hotelops.ui.presentation.authentication.AuthViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCustomerUI(navController: NavController, userId: String) {
    val viewModel = viewModel(modelClass = CustomerViewModel::class.java)
    val customers by viewModel.customers.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        viewModel.getCustomerById(userId)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Customer List") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (customers.isEmpty()) {
                Text(text = "No customers found.", style = MaterialTheme.typography.bodyLarge)
            } else {
                LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(customers.reversed()) { customer ->
                        CustomerItem(customer)
                    }
                }
            }
        }
    }
}

@Composable
fun CustomerItem(customer: Customer) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "CustomerId: ${customer.customerId}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Name: ${customer.customername}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "Email: ${customer.emailId}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = "Contact: ${customer.contactnumber}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(text = "Address: ${customer.address}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
