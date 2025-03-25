package com.test.hotelops.roomdatabase.repository.Repository

import com.test.hotelops.roomdatabase.entity.Booking
import com.test.hotelops.roomdatabase.repository.dao.BookingDao

class BookingRepository(private val bookingDao: BookingDao) {

    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }

    suspend fun deleteBooking(booking: Booking) {
        bookingDao.deleteBooking(booking)
    }

    suspend fun updateBooking(booking: Booking) {
        bookingDao.updateBooking(booking)
    }

    suspend fun getBookingByI(bookingId: String):  List<Booking>? {
        return bookingDao.getBookingByI(bookingId)
    }

    suspend fun getBookingById(bookingId: String):  List<Booking>? {
        return bookingDao.getBookingById(bookingId)
    }

    suspend fun getAllBookings(): List<Booking> {
        return bookingDao.getAllBookings()
    }
}
