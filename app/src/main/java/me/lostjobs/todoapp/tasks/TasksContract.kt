package me.lostjobs.todoapp.tasks

import me.lostjobs.todoapp.BasePresenter
import me.lostjobs.todoapp.BaseView
import me.lostjobs.todoapp.data.Task

/**
 * @author lostjobs created on 14/11/2017
 */
interface TasksContract {

  enum class TaskFilterType {
    /**
     * Do not filter tasks
     */
    ALL_TASKS,
    /**
     * Filters only the active (not complete yet) tasks.
     */
    ACTIVE_TASKS,
    /**
     * Filters only the completed tasks
     */
    COMPLETED_TASKS
  }

  interface Presenter : BasePresenter {
    fun result(resultCode: Int, requestCode: Int)
    fun loadTasks(forceUpdate: Boolean)
    fun addNewTask()
    fun openTaskDetails(requestTask: Task)
    fun completeTask()
    fun activateTask()
    fun clearCompletedTasks()
    fun setFilter(filterType: TaskFilterType)
    fun taskFilter(): TaskFilterType
  }

  interface View : BaseView<Presenter>
}