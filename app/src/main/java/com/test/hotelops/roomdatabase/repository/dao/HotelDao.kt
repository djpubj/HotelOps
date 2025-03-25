package com.test.hotelops.roomdatabase.repository.dao

import androidx.room.Dao
import androidx.room.Query
import com.test.hotelops.roomdatabase.entity.Hotel
import com.test.hotelops.roomdatabase.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface HotelDao {
    @Query("INSERT INTO Hotels(hotelId,hotelname ) VALUES(:hotelId,:hotelname);")
    suspend fun inserthotel(
        hotelId: String,
        hotelname:String,
    )

    @Query("DELETE FROM Hotels WHERE hotelId =:hotelId;")
    suspend fun deletehotel(hotelId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM Hotels WHERE hotelId = :hotelId);")
    suspend fun checkhotel(hotelId: String): Boolean

    @Query("SELECT * FROM hotels WHERE hotelId = :hotelId ;")
    suspend fun gethotelById(hotelId: String): Hotel?
}