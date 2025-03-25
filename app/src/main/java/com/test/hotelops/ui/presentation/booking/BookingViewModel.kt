package com.test.hotelops.ui.presentation.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.hotelops.roomdatabase.entity.Booking
import com.test.hotelops.roomdatabase.repository.Graph
import com.test.hotelops.roomdatabase.repository.Repository.BookingRepository
import com.test.hotelops.roomdatabase.repository.Repository.HotelRepository

class BookingViewModel(private val repository: BookingRepository = Graph.BookingRepository) : ViewModel() {

    private val _bookings = MutableLiveData<List<Booking>>()
    val bookings: LiveData<List<Booking>> get() = _bookings

    private val _bookingById = MutableLiveData<List<Booking>>() // Ensure non-null LiveData
    val bookingById: LiveData<List<Booking>> get() = _bookingById

    fun insertBooking(booking: Booking) {
        viewModelScope.launch {
            repository.insertBooking(booking)
            refreshBookings()
        }
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch {
            repository.deleteBooking(booking)
            refreshBookings()
        }
    }

    fun updateBooking(booking: Booking) {
        viewModelScope.launch {
            repository.updateBooking(booking)
            refreshBookings()
        }
    }

    fun getBookingById(bookingId: String) {
        viewModelScope.launch {
            _bookingById.postValue(repository.getBookingById(bookingId))
        }
    }
    private fun refreshBookings() {
        viewModelScope.launch {
            _bookings.postValue(repository.getAllBookings())
        }
    }
}
