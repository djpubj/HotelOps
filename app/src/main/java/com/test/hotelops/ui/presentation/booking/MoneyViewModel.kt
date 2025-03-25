package com.test.hotelops.ui.presentation.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.hotelops.roomdatabase.entity.Money
import com.test.hotelops.roomdatabase.repository.Graph
import com.test.hotelops.roomdatabase.repository.Repository.MoneyRepository
import kotlinx.coroutines.launch

class MoneyViewModel(private val repository:MoneyRepository= Graph.moneyRepository) : ViewModel() {

    private val _moneyList: MutableLiveData<List<Money>> = MutableLiveData()
    val moneyList: LiveData<List<Money>> get() = _moneyList

    fun insertMoney(money: Money) {
        viewModelScope.launch {
            repository.insertMoney(money)
            refreshMoneyList()
        }
    }

    fun deleteMoney(money: Money) {
        viewModelScope.launch {
            repository.deleteMoney(money)
            refreshMoneyList()
        }
    }

    fun updateMoney(money: Money) {
        viewModelScope.launch {
            repository.updateMoney(money)
            refreshMoneyList()
        }
    }

    fun getMoneyById(transactionId: String) {
        viewModelScope.launch {
            _moneyList.postValue(listOfNotNull(repository.getMoneyById(transactionId)))
        }
    }

    private fun refreshMoneyList() {
        viewModelScope.launch {
            _moneyList.postValue(repository.getAllMoney())
        }
    }
}
