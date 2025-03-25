package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "AllRooms",
    foreignKeys = [ForeignKey(
        entity = Hotel::class,
        parentColumns = ["hotelId"],
        childColumns = ["hotelId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    )])
data class Room(
    @PrimaryKey(autoGenerate = true)
    var roomnumber: Int=0,
    var hotelId: String = "",
    var status:String="",
)