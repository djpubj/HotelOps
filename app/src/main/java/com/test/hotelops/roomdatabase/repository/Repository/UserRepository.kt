package com.test.hotelops.roomdatabase.repository.Repository

import com.test.hotelops.roomdatabase.entity.User
import com.test.hotelops.roomdatabase.repository.dao.UserDao

class UserRepository(
    private val userDao: UserDao
) {
    suspend fun insertCard(user: User) {
        userDao.insertUser(
            userId = user.userId,
            username = user.username,
            userpassword = user.userpassword,
            emailId = user.emailId,
            hotelId = user.hotelId
        )
    }

    suspend fun getUserById(userId: String, password: String): User? {
        return userDao.getUserById(userId = userId, password = password)
    }
    suspend fun getUserByonlyId(userId: String): User? {
        return userDao.getUserByonlyId(userId = userId)
    }

    suspend fun checkuser(userId: String, password: String): Boolean {
        return userDao.checkuser(userId = userId, password = password)
    }

    suspend fun updateUser(username: String, userId: String) {
        userDao.updateUser(username = username, userId = userId)
    }

    suspend fun deleteUser(userId: String) {
        userDao.deleteUser(userId = userId)
    }
}