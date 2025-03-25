package com.test.hotelops.roomdatabase.repository.Repository

import com.test.hotelops.roomdatabase.entity.Money
import com.test.hotelops.roomdatabase.repository.dao.MoneyDao

class MoneyRepository(private val moneyDao: MoneyDao) {

    suspend fun insertMoney(money: Money) {
        moneyDao.insertMoney(money)
    }

    suspend fun deleteMoney(money: Money) {
        moneyDao.deleteMoney(money)
    }

    suspend fun updateMoney(money: Money) {
        moneyDao.updateMoney(money)
    }

    suspend fun getMoneyById(transactionId: String): Money? {
        return moneyDao.getMoneyById(transactionId)
    }

    suspend fun getAllMoney(): List<Money> {
        return moneyDao.getAllMoney()
    }
}
