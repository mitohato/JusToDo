package com.ict.mito.justodo.domain.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ict.mito.justodo.domain.ToDoInfo

/**
 * Created by mito on 2018-12-18.
 */
@Dao
interface ToDoInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createToDo(toDoInfo: ToDoInfo)

    @Query("SELECT * FROM justodo_todo_table ORDER BY id ASC")
    fun findAll(): LiveData<List<ToDoInfo>>

    @Update
    fun updateToDoInfo(toDoInfo: ToDoInfo)

    @Delete
    fun deleteToDoInfo(toDoInfo: ToDoInfo)
}