package com.test.hotelops.roomdatabase.repository.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.hotelops.roomdatabase.entity.Money

@Dao
interface MoneyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoney(money: Money)

    @Delete
    suspend fun deleteMoney(money: Money)

    @Update
    suspend fun updateMoney(money: Money)

    @Query("SELECT * FROM Money WHERE transactionId = :transactionId")
    suspend fun getMoneyById(transactionId: String): Money?

    @Query("SELECT * FROM Money")
    suspend fun getAllMoney(): List<Money>
}