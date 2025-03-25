package com.test.hotelops.ui.presentation.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.hotelops.roomdatabase.entity.User
import com.test.hotelops.roomdatabase.repository.Graph
import com.test.hotelops.roomdatabase.repository.Repository.UserRepository
import kotlinx.coroutines.launch

class AuthViewmodel(
    private val repository: UserRepository = Graph.userRepository
) : ViewModel() {

    private val _fetchuser = MutableLiveData<User?>()
    val fetchuser: LiveData<User?> = _fetchuser

    private val _isUserFetched = MutableLiveData<Boolean>()
    val isUserFetched: LiveData<Boolean> = _isUserFetched

    fun adduser(user: User) {
        viewModelScope.launch { repository.insertCard(user) }
    }

    fun getUserById(userId: String, password: String) {
        viewModelScope.launch { _fetchuser.value = repository.getUserById(userId, password) }
    }
    fun getUserByonlyId(userId: String,) {
        viewModelScope.launch { _fetchuser.value = repository.getUserByonlyId(userId) }
    }

    fun checkuser(userId: String, password: String) {
        viewModelScope.launch { _isUserFetched.value = repository.checkuser(userId, password) }
    }

    fun updateUser(username: String, userId: String) {
        viewModelScope.launch { repository.updateUser(username, userId) }
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch { repository.deleteUser(userId) }
    }
}