package com.test.hotelops.roomdatabase.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.test.hotelops.roomdatabase.entity.Booking
import com.test.hotelops.roomdatabase.entity.Customer
import com.test.hotelops.roomdatabase.entity.Hotel
import com.test.hotelops.roomdatabase.entity.Money
import com.test.hotelops.roomdatabase.entity.User
import com.test.hotelops.roomdatabase.repository.dao.BookingDao
import com.test.hotelops.roomdatabase.repository.dao.CustomerDao
import com.test.hotelops.roomdatabase.repository.dao.HotelDao
import com.test.hotelops.roomdatabase.repository.dao.MoneyDao
import com.test.hotelops.roomdatabase.repository.dao.UserDao
import kotlinx.coroutines.InternalCoroutinesApi


@Database(
    entities = [User::class, Hotel::class, Customer::class, Booking::class, Money::class],
    version = 10,
    exportSchema = false
)
abstract class Appdb : RoomDatabase() {
    companion object {
        @Volatile
        var INSTANCE: Appdb? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getdatabase(context: Context): Appdb {
            return INSTANCE ?: kotlinx.coroutines.internal.synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, Appdb::class.java, "hotel_ops"
                ).fallbackToDestructiveMigration().addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        // Create triggers here
                        db.execSQL(
                            """
                            CREATE TRIGGER insert_money_after_booking
                            AFTER INSERT ON AllBooking
                            FOR EACH ROW
                            BEGIN
                                INSERT INTO Money (transactionId, bookingId, hotelId, payment)
                                VALUES (NEW.bookingId, NEW.bookingId, NEW.hotelId, NEW.payment);
                            END;
                        """.trimIndent()
                        )
                    }
                })
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        const val name = "hotel_ops"

    }


    abstract fun getUserDao(): UserDao
    abstract fun getHotelDao(): HotelDao
    abstract fun getCustomerDao(): CustomerDao
    abstract fun getBookingDao(): BookingDao
    abstract fun getMoneyDao(): MoneyDao

}