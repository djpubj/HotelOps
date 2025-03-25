package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Money")
data class Money(
    @PrimaryKey var transactionId: String = generateUserId(),
    var bookingId:String,
    var hotelId:String,
    var payment:Int
)