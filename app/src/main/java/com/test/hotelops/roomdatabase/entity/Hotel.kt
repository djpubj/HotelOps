package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "hotels")
data class Hotel(
    @PrimaryKey
    var hotelId:  String = generateUserId(),
    var hotelname: String = "",
)