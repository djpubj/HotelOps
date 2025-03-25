package com.test.hotelops.roomdatabase.repository.dao

import androidx.room.Dao
import androidx.room.Query
import com.test.hotelops.roomdatabase.entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("INSERT INTO users(userId, username, userpassword,emailId,hotelId ) VALUES(:userId,:username,:userpassword,:emailId,:hotelId);")
    suspend fun insertUser(
        userId: String,
        username: String,
        userpassword: String,
        emailId: String,
        hotelId: String
    )

    @Query("UPDATE users SET username =:username WHERE userId =:userId;")
    suspend fun updateUser(username: String, userId: String)

    @Query("DELETE FROM users WHERE userId =:userId;")
    suspend fun deleteUser(userId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE userId = :userId and userpassword=:password);")
    suspend fun checkuser(userId: String, password: String): Boolean

    @Query("SELECT * FROM users WHERE userId = :userId and userpassword=:password;")
    suspend fun getUserById(userId: String, password: String): User?

    @Query("SELECT * FROM users WHERE userId = :userId ;")
    suspend fun getUserByonlyId(userId: String): User?

    @Query("SELECT * FROM users;")
    fun getAllUsers(): Flow<List<User>>
}