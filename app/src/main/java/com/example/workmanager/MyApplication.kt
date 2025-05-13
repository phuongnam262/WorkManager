package com.example.workmanager

import android.app.Application
import android.content.Context
import android.util.Log  // ✅ Import đúng
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.stevdza.san.workmanagement.api.DemoApi
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: CustomWorkerFactory  // ✅ Sửa tên class

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)  // ✅ Sửa thành Log.DEBUG
            .setWorkerFactory(workerFactory)
            .build()
}

class CustomWorkerFactory @Inject constructor(  // ✅ Tên đúng
    private val api: DemoApi
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = CustomWorker(api, appContext, workerParameters)  // ✅
}