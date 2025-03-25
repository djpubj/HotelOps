package com.test.hotelops.ui.presentation.previouscustomer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.hotelops.roomdatabase.entity.Customer
import com.test.hotelops.roomdatabase.repository.Graph
import com.test.hotelops.roomdatabase.repository.Repository.CustomerRepository
import com.test.hotelops.roomdatabase.repository.Repository.HotelRepository

class CustomerViewModel(private val repository: CustomerRepository = Graph.customerRepository) : ViewModel() {

    private val _customers = MutableLiveData<List<Customer>>()
    val customers: LiveData<List<Customer>> get() = _customers

    fun insertCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.insertCustomer(customer)

        }
    }

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.deleteCustomer(customer)

        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.updateCustomer(customer)

        }
    }

    fun getCustomerById(userId: String) {
        viewModelScope.launch {
            _customers.postValue(repository.getCustomerById(userId))
        }
    }

}
