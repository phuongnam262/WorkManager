package com.example.workmanager

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class CustomWorker(
    appContext: Context,
    workerParams: WorkerParameters,
): Worker(appContext, workerParams) {
    override fun doWork(): Result {
        println("Hello from Worker")
        return Result.success()
    }

}