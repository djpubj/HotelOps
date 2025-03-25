package com.test.hotelops.roomdatabase.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.hotelops.roomdatabase.entity.Booking

@Dao
interface BookingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: Booking)

    @Delete
    suspend fun deleteBooking(booking: Booking)

    @Update
    suspend fun updateBooking(booking: Booking)

    @Query("SELECT b.* FROM AllBooking b JOIN Users u ON b.hotelId = u.hotelId WHERE u.userId = :userId")
    suspend fun getBookingByI(userId: String):  List<Booking>?

    @Query("SELECT * FROM AllBooking b WHERE b.hotelId = (SELECT hotelId FROM Users WHERE userId = :userId);")
    suspend fun getBookingById(userId: String):  List<Booking>?

    @Query("SELECT * FROM AllBooking")
    suspend fun getAllBookings(): List<Booking>
}
