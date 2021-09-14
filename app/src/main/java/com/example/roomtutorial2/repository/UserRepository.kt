package com.example.roomtutorial2.repository

import androidx.lifecycle.LiveData
import com.example.roomtutorial2.data.UserDao
import com.example.roomtutorial2.model.User

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

}