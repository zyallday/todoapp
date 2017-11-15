package me.lostjobs.todoapp.tasks

import android.support.v4.app.Fragment
import me.lostjobs.todoapp.tasks.TasksContract.Presenter

/**
 * @author lostjobs created on 14/11/2017
 */
class TasksFragment : Fragment(), TasksContract.View {

  companion object {
    fun newInstance(): TasksFragment = TasksFragment()
  }

  override fun setPresenter(presenter: Presenter) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}