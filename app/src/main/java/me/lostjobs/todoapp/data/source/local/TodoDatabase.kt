package me.lostjobs.todoapp.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import me.lostjobs.todoapp.data.Task

/**
 * @author lostjobs created on 15/11/2017
 */
@Database(entities = arrayOf(Task::class), version = 1)
abstract class TodoDatabase : RoomDatabase() {

  abstract fun taskDao(): TasksDao

  companion object {
    @Volatile private var INSTANCE: TodoDatabase? = null

    fun getInstance(context: Context): TodoDatabase = INSTANCE ?: synchronized(this) {
      INSTANCE ?: buildInstance(context).also { INSTANCE = it }
    }

    private fun buildInstance(context: Context): TodoDatabase = Room.databaseBuilder(
        context.applicationContext, TodoDatabase::class.java, "Tasks.db").build()
  }
}