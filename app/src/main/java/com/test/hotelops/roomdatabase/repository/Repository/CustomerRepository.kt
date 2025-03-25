package com.test.hotelops.roomdatabase.repository.Repository

import com.test.hotelops.roomdatabase.entity.Customer
import com.test.hotelops.roomdatabase.repository.dao.CustomerDao

class CustomerRepository(private val customerDao: CustomerDao) {

    suspend fun insertCustomer(customer: Customer) {
        customerDao.insertCustomer(customer)
    }

    suspend fun deleteCustomer(customer: Customer) {
        customerDao.deleteCustomer(customer)
    }

    suspend fun updateCustomer(customer: Customer) {
        customerDao.updateCustomer(customer)
    }

    suspend fun getCustomerById(customerId: String):  List<Customer>? {
        return customerDao.getCustomerById(customerId)
    }

    suspend fun getAllCustomers(): List<Customer> {
        return customerDao.getAllCustomers()
    }
}
