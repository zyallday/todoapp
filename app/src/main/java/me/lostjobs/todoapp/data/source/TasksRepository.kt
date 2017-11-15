package me.lostjobs.todoapp.data.source

import me.lostjobs.todoapp.data.Task
import me.lostjobs.todoapp.data.source.TasksDataSource.GetTaskCallback
import me.lostjobs.todoapp.data.source.TasksDataSource.LoadTasksCallback

/**
 * @author lostjobs created on 15/11/2017
 */
class TasksRepository private constructor(val tasksLocalDataSource: TasksDataSource,
    val tasksRemoteDataSource: TasksDataSource) : TasksDataSource {

  private var mCachedTasks = HashMap<String, Task>()
  private val mCacheIsDirty = false

  companion object {
    @Volatile private var INSTANCE: TasksRepository? = null

    fun getInstance(tasksLocalDataSource: TasksDataSource,
        tasksRemoteDataSource: TasksDataSource) = INSTANCE ?: synchronized(this) {
      INSTANCE ?: TasksRepository(tasksLocalDataSource,
          tasksRemoteDataSource).also { INSTANCE = it }
    }

    fun destroyInstance() {
      INSTANCE = null
    }
  }

  override fun getTasks(callback: LoadTasksCallback) {
    if (!mCachedTasks.isEmpty() && !mCacheIsDirty) {
      callback.onTasksLoaded(mCachedTasks.values.toList())
      return
    }

  }

  override fun getTask(taskId: String, callback: GetTaskCallback) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun saveTask(task: Task) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun completeTask(taskId: String) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun completeTask(task: Task) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun activeTask(taskId: String) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun activeTask(task: Task) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun clearCompletedTasks() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun refreshTasks() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteAllTasks() {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun deleteTask(taskId: String) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}