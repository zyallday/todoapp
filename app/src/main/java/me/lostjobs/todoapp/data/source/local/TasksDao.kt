package me.lostjobs.todoapp.data.source.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import me.lostjobs.todoapp.data.Task

/**
 * @author lostjobs created on 15/11/2017
 */
@Dao
interface TasksDao {
  @Query("SELECT * FROM Tasks")
  fun getTasks(): List<Task>

  @Query("SELECT * FROM Tasks WHERE entryId = :taskId")
  fun getTask(taskId: String): Task?

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(task: Task)

  @Update
  fun updateTask(task: Task)

  @Query("UPDATE tasks SET completed = :completed WHERE entryId = :taskId")
  fun updateCompleted(taskId: String, completed: Boolean)

  @Query("DELETE FROM Tasks WHERE entryId = :taskId")
  fun deleteTaskById(taskId: String)

  @Query("DELETE FROM Tasks")
  fun deleteTasks()

  @Query("DELETE FROM Tasks WHERE completed = 1")
  fun deleteCompletedTasks(): Int

}