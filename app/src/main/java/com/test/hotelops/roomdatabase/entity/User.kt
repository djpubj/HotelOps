package com.test.hotelops.roomdatabase.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "users",
    foreignKeys = [ForeignKey(
        entity = Hotel::class,
        parentColumns = ["hotelId"],
        childColumns = ["hotelId"],
        onDelete = ForeignKey.CASCADE // Ensures deletion propagation
    )])
data class User(
    @PrimaryKey
    var userId: String = generateUserId(),
    var username: String = "",
    var userpassword: String = "",
    var emailId: String ="",
    var hotelId: String=""
)

fun generateUserId(): String {
    val chars = "abcdefghijklmnopqrstuvwxyz0123456789"
    return (1..4)
        .map { chars.random() }
        .joinToString("")
}