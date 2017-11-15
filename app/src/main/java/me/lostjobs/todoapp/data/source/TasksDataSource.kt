package me.lostjobs.todoapp.data.source

import me.lostjobs.todoapp.data.Task
import me.lostjobs.todoapp.data.source.TasksDataSource.GetTaskCallback
import me.lostjobs.todoapp.data.source.TasksDataSource.LoadTasksCallback

/**
 * @author lostjobs created on 14/11/2017
 */

interface TasksDataSource {
  interface LoadTasksCallback {
    fun onTasksLoaded(tasks: List<Task>)
    fun onDataNotAvailable()
  }

  interface GetTaskCallback {
    fun onTaskLoaded(task: Task)
    fun onDataNotAvailable()
  }

  fun getTasks(callback: LoadTasksCallback)
  fun getTask(taskId: String, callback: GetTaskCallback)
  fun saveTask(task: Task)
  fun completeTask(taskId: String)
  fun completeTask(task: Task)
  fun activeTask(taskId: String)
  fun activeTask(task: Task)
  fun clearCompletedTasks()
  fun refreshTasks()
  fun deleteAllTasks()
  fun deleteTask(taskId: String)
}

