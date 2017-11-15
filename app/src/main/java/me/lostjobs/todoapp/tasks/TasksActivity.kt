package me.lostjobs.todoapp.tasks

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tasks.drawer_layout
import kotlinx.android.synthetic.main.activity_tasks.nav_view
import kotlinx.android.synthetic.main.activity_tasks.toolbar
import me.lostjobs.todoapp.Injection
import me.lostjobs.todoapp.R.color
import me.lostjobs.todoapp.R.drawable
import me.lostjobs.todoapp.R.id
import me.lostjobs.todoapp.R.layout
import me.lostjobs.todoapp.statistics.StatisticsActivity

class TasksActivity : AppCompatActivity() {

  lateinit var mTasksPresenter: TasksContract.Presenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_tasks)
    setSupportActionBar(toolbar)
    supportActionBar?.setHomeAsUpIndicator(drawable.ic_menu)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    drawer_layout.setStatusBarBackground(color.colorPrimaryDark)
    nav_view.setNavigationItemSelectedListener {
      when (it.itemId) {
        id.statistics_navigation_menu_item -> startActivity(
            Intent(TasksActivity@ this, StatisticsActivity::class.java))
      }
      it.isCheckable = true
      drawer_layout.closeDrawers()
      true
    }

    val tasksFragment = supportFragmentManager?.findFragmentById(
        id.contentFrame) as TasksFragment?
    if (null == tasksFragment) {
      supportFragmentManager?.beginTransaction()?.add(id.contentFrame,
          TasksFragment.newInstance())?.commit()
    }

    mTasksPresenter = TasksPresenter(Injection.provideTasksRepository(applicationContext), tasksFragment)

  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      android.R.id.home -> drawer_layout.openDrawer(GravityCompat.START)
    }
    return super.onOptionsItemSelected(item)
  }


  override fun onSaveInstanceState(outState: Bundle?) {
    outState?.putSerializable(CURRENT_FILTERING_KEY, mTasksPresenter.taskFilter())
    super.onSaveInstanceState(outState)
  }

  companion object {
    const val CURRENT_FILTERING_KEY = "CURRENT_FILTERING_KEY"
  }
}
