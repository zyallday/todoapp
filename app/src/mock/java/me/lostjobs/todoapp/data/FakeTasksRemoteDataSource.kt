package me.lostjobs.todoapp.data

import me.lostjobs.todoapp.data.source.TasksDataSource
import me.lostjobs.todoapp.data.source.TasksDataSource.GetTaskCallback
import me.lostjobs.todoapp.data.source.TasksDataSource.LoadTasksCallback

/**
 * @author lostjobs created on 15/11/2017
 */
class FakeTasksRemoteDataSource : TasksDataSource {

  companion object {
    @Volatile private var INSTANCE: TasksDataSource? = null
    fun getInstance() = INSTANCE ?: synchronized(this) {
      INSTANCE ?: FakeTasksRemoteDataSource().also { INSTANCE = it }
    }
  }

  override fun getTasks(callback: LoadTasksCallback) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
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