package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "AllCustomer",
    foreignKeys = [ForeignKey(
        entity = Hotel::class,
        parentColumns = ["hotelId"],
        childColumns = ["hotelId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    )])
data class Customer(
    @PrimaryKey
    var customerId:  String = generateUserId(),
    var hotelId: String = "",
    var customername:String,
    var emailId:String,
    var contactnumber: String,
    var address:String
)