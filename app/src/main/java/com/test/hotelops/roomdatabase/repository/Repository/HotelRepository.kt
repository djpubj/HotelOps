package com.test.hotelops.roomdatabase.repository.Repository

import com.test.hotelops.roomdatabase.entity.Hotel
import com.test.hotelops.roomdatabase.repository.dao.HotelDao

class HotelRepository(private val hotelDao: HotelDao) {

    suspend fun insertHotel(hotel: Hotel) {
        hotelDao.inserthotel(hotel.hotelId, hotel.hotelname)
    }

    suspend fun deleteHotel(hotelId: String) {
        hotelDao.deletehotel(hotelId)
    }

    suspend fun checkHotelExists(hotelId: String): Boolean {
        return hotelDao.checkhotel(hotelId)
    }

    suspend fun getHotelById(hotelId: String): Hotel? {
        return hotelDao.gethotelById(hotelId)
    }
}
