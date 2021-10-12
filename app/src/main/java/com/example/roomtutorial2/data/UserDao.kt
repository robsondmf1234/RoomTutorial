package com.example.roomtutorial2.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomtutorial2.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    suspend fun updateUser(user: User)

}