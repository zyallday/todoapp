package me.lostjobs.todoapp

/**
 * @author lostjobs created on 14/11/2017
 */

interface BasePresenter {
  fun start()
}

interface BaseView<in T : BasePresenter> {
  fun setPresenter(presenter: T)
}