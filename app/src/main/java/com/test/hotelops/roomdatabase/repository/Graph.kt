package com.test.hotelops.roomdatabase.repository

import android.content.Context
import com.test.hotelops.roomdatabase.repository.Repository.BookingRepository
import com.test.hotelops.roomdatabase.repository.Repository.CustomerRepository
import com.test.hotelops.roomdatabase.repository.Repository.HotelRepository
import com.test.hotelops.roomdatabase.repository.Repository.MoneyRepository
import com.test.hotelops.roomdatabase.repository.Repository.UserRepository

object Graph {
    lateinit var db: Appdb
        private set

    val userRepository by lazy {
        UserRepository(userDao = db.getUserDao())
    }
    val hotelRepository by lazy {
        HotelRepository(hotelDao = db.getHotelDao())
    }
    val customerRepository by lazy {
        CustomerRepository(customerDao = db.getCustomerDao())
    }
    val moneyRepository by lazy {
        MoneyRepository(moneyDao = db.getMoneyDao())
    }
    val BookingRepository by lazy {
        BookingRepository(bookingDao = db.getBookingDao())
    }


    fun provide(context: Context) {
        db = Appdb.getdatabase(context)
    }
}

