package com.test.hotelops.roomdatabase.repository.dao

import androidx.room.*
import com.test.hotelops.roomdatabase.entity.Customer

@Dao
interface CustomerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer: Customer)

    @Delete
    suspend fun deleteCustomer(customer: Customer)

    @Update
    suspend fun updateCustomer(customer: Customer)

    @Query("SELECT c.* FROM AllCustomer c INNER JOIN AllBooking b ON c.customerId = b.customerId WHERE b.hotelId = (SELECT hotelId FROM Users WHERE userId = :userId);")
    suspend fun getCustomerById(userId: String): List<Customer>?

    @Query("SELECT * FROM AllCustomer")
    suspend fun getAllCustomers(): List<Customer>
}
