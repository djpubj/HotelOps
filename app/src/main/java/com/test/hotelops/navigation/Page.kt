package com.test.hotelops.navigation

import kotlinx.serialization.Serializable

@Serializable
object login

@Serializable
object create

@Serializable
data class home(
    val userId:String
)

@Serializable
object createhotel

@Serializable
data class createbooking(
    val userId:String
)

@Serializable
data class allcustomer(
    val userId:String
)

