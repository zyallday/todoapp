package me.lostjobs.todoapp

import android.content.Context
import me.lostjobs.todoapp.data.FakeTasksRemoteDataSource
import me.lostjobs.todoapp.data.source.TasksRepository
import me.lostjobs.todoapp.data.source.local.TasksLocalDataSource
import me.lostjobs.todoapp.data.source.local.TodoDatabase
import me.lostjobs.todoapp.util.AppExecutors

/**
 * @author lostjobs created on 14/11/2017
 */
class Injection {
  companion object {
    fun provideTasksRepository(context: Context): TasksRepository {
      val instance = TodoDatabase.getInstance(context)
      return TasksRepository.getInstance(FakeTasksRemoteDataSource.getInstance(),
          TasksLocalDataSource.getInstance(
              AppExecutors(), instance.taskDao()))
    }
  }
}