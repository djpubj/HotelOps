package com.test.hotelops.ui.presentation.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.hotelops.roomdatabase.entity.Hotel
import com.test.hotelops.roomdatabase.repository.Graph
import com.test.hotelops.roomdatabase.repository.Repository.HotelRepository
import kotlinx.coroutines.launch

class HotelViewModel(
    private val repository: HotelRepository = Graph.hotelRepository
) : ViewModel() {

    private val _hotelExists = MutableLiveData<Boolean>()
    val hotelExists: LiveData<Boolean> get() = _hotelExists

    private val _hotelData = MutableLiveData<Hotel?>()
    val hotelData: LiveData<Hotel?> get() = _hotelData

    fun insertHotel(hotel: Hotel) {
        viewModelScope.launch {
            repository.insertHotel(hotel)
        }
    }

    fun deleteHotel(hotelId: String) {
        viewModelScope.launch {
            repository.deleteHotel(hotelId)
        }
    }

    fun checkHotel(hotelId: String) {
        viewModelScope.launch {
            _hotelExists.postValue(repository.checkHotelExists(hotelId))
        }
    }

    fun getHotel(hotelId: String) {
        viewModelScope.launch {
            _hotelData.postValue(repository.getHotelById(hotelId))
        }
    }
}
