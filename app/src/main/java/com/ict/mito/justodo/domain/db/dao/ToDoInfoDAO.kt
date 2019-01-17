package com.ict.mito.justodo.domain.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ict.mito.justodo.domain.ToDoInfo
import io.reactivex.Single

/**
 * Created by mito on 2018-12-18.
 */
@Dao
interface ToDoInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(toDoInfo: ToDoInfo)

    @Query("SELECT * FROM justodo_todo_table")
    fun getAllToDo(): Single<List<ToDoInfo>>

    @Update
    fun update(toDoInfo: ToDoInfo)

    @Delete
    fun delete(toDoInfo: ToDoInfo)
}