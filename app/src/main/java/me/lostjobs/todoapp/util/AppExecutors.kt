package me.lostjobs.todoapp.util

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * @author lostjobs created on 15/11/2017
 */
class AppExecutors(private val diskIO: Executor, private val networkIO: Executor,
    private val mainThread: MainThreadExecutor = MainThreadExecutor()) {

  constructor() : this(diskIO = Executors.newSingleThreadExecutor(),
      networkIO = Executors.newFixedThreadPool(
          THREAD_COUNT))

  companion object {
    private const val THREAD_COUNT = 3
  }

  fun diskIO() = diskIO
  fun networkIO() = networkIO
  fun mainThread() = mainThread

  class MainThreadExecutor : Executor {
    companion object {
      val mainHandler: Handler = Handler(Looper.getMainLooper())
    }

    override fun execute(command: Runnable) {
      mainHandler.post(command)
    }

  }
}