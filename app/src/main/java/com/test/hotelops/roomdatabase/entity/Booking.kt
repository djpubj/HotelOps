package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "AllBooking", foreignKeys = [ForeignKey(
        entity = Hotel::class,
        parentColumns = ["hotelId"],
        childColumns = ["hotelId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    ), ForeignKey(
        entity = User::class,
        parentColumns = ["userId"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    ), ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    )]
)
data class Booking(
    @PrimaryKey var bookingId: String = generateUserId(),
    var userId: String = "",
    var customerId: String = "",
    var roomnumber: String = "",
    var hotelId: String = "",
    var payment:Int=0,
)