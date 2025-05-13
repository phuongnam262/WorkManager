package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.stevdza.san.workmanagement.api.DemoApi
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CustomWorker @AssistedInject constructor(
    @Assisted private val api: DemoApi,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
): CoroutineWorker(appContext, workerParams) {
    suspend override fun doWork(): Result {
       try {
           val response = api.getPost()
           if (response.isSuccessful) {
               Log.d("CustomWorker", "Success")
               Log.d("CustomWorker", "ID: ${response.body()?.id} Title: ${response.body()?.title}")

               return Result.success()
           } else {
               Log.d("CustomWorker", "Retry....")
               return Result.retry()
           }
       }catch(e: Exception) {
           Log.d("CustomWorker", "Failure")
           return Result.failure()
       }
    }
}