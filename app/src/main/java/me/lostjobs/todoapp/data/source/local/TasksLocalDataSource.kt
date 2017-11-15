package me.lostjobs.todoapp.data.source.local

import me.lostjobs.todoapp.data.Task
import me.lostjobs.todoapp.data.source.TasksDataSource
import me.lostjobs.todoapp.data.source.TasksDataSource.GetTaskCallback
import me.lostjobs.todoapp.data.source.TasksDataSource.LoadTasksCallback
import me.lostjobs.todoapp.util.AppExecutors

/**
 * @author lostjobs created on 15/11/2017
 */
class TasksLocalDataSource private constructor(private val appExecutors: AppExecutors,
    private val mTasksDao: TasksDao) : TasksDataSource {

  companion object {
    @Volatile private var INSTANCE: TasksLocalDataSource? = null

    fun getInstance(appExecutors: AppExecutors, tasksDao: TasksDao) = INSTANCE ?: synchronized(
        this) {
      INSTANCE ?: TasksLocalDataSource(appExecutors, tasksDao).also { INSTANCE = it }
    }
  }

  override fun getTasks(callback: LoadTasksCallback) {
    appExecutors.diskIO().execute {
      val tasks = mTasksDao.getTasks()
      appExecutors.mainThread().execute {
        when (tasks.isEmpty()) {
          true -> callback.onDataNotAvailable()
          else -> callback.onTasksLoaded(tasks)
        }
      }
    }
  }

  override fun getTask(taskId: String, callback: GetTaskCallback) {
    appExecutors.diskIO().execute {
      val task = mTasksDao.getTask(taskId)
      appExecutors.mainThread().execute {
        if (null != task) {
          callback.onTaskLoaded(task)
        } else {
          callback.onDataNotAvailable()
        }
      }
    }
  }

  override fun saveTask(task: Task) {
    appExecutors.diskIO().execute { mTasksDao.insert(task) }
  }

  override fun completeTask(taskId: String) {
    appExecutors.diskIO().execute { mTasksDao.updateCompleted(taskId, true) }
  }

  override fun completeTask(task: Task) {
    appExecutors.diskIO().execute { mTasksDao.updateCompleted(task.taskId, true) }
  }

  override fun activeTask(taskId: String) {
    appExecutors.diskIO().execute { mTasksDao.updateCompleted(taskId, false) }
  }

  override fun activeTask(task: Task) {
    appExecutors.diskIO().execute { mTasksDao.updateCompleted(task.taskId, false) }
  }

  override fun clearCompletedTasks() {
    appExecutors.diskIO().execute { mTasksDao.deleteCompletedTasks() }
  }

  override fun refreshTasks() {

  }

  override fun deleteAllTasks() {
    appExecutors.diskIO().execute { mTasksDao.deleteTasks() }
  }

  override fun deleteTask(taskId: String) {
    appExecutors.diskIO().execute { mTasksDao.deleteTaskById(taskId) }
  }
}