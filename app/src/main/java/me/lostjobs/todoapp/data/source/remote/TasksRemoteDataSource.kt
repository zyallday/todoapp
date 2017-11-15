package me.lostjobs.todoapp.data.source.remote

import android.os.Handler
import android.os.Looper
import me.lostjobs.todoapp.data.Task
import me.lostjobs.todoapp.data.source.TasksDataSource
import me.lostjobs.todoapp.data.source.TasksDataSource.GetTaskCallback
import me.lostjobs.todoapp.data.source.TasksDataSource.LoadTasksCallback

/**
 * @author lostjobs created on 15/11/2017
 */
class TasksRemoteDataSource : TasksDataSource {

  private val mainHandler: Handler = Handler(Looper.getMainLooper())

  companion object {
    @Volatile private var INSTANCE: TasksDataSource? = null
    private const val SERVICE_LATENCY_IN_MILLIS = 5000L
    private val TASKS_SERVICE_DATA: MutableMap<String, Task>

    init {
      TASKS_SERVICE_DATA = LinkedHashMap(2)
      addTask("Build tower in Pisa", "Ground looks good, no foundation work required.")
      addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!")
    }

    private fun addTask(title: String, description: String) {
      val newTask = Task(mTitle = title, mDescription = description)
      TASKS_SERVICE_DATA.put(newTask.taskId, newTask)
    }
  }

  override fun getTasks(callback: LoadTasksCallback) {
    fakeNetworkIO(Runnable { callback.onTasksLoaded(TASKS_SERVICE_DATA.values.toList()) })
  }

  override fun getTask(taskId: String, callback: GetTaskCallback) {
    mainHandler.postDelayed({
      val task = TASKS_SERVICE_DATA[taskId]
      if (null == task) {
        callback.onDataNotAvailable()
      } else {
        callback.onTaskLoaded(task)
      }
    }, SERVICE_LATENCY_IN_MILLIS)
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

  private fun fakeNetworkIO(r: Runnable) {
    mainHandler.postDelayed(r, SERVICE_LATENCY_IN_MILLIS)
  }

}