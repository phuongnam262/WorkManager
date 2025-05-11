package com.example.workmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.workmanager.ui.theme.WorkManagerTheme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val workRequest = OneTimeWorkRequestBuilder<CustomWorker>()
            .setInitialDelay(10, TimeUnit.SECONDS)
            .setBackoffCriteria(
                backoffPolicy = androidx.work.BackoffPolicy.LINEAR,
                backoffDelay = 15,
                timeUnit = TimeUnit.SECONDS
            )

            .build()
        WorkManager.getInstance(applicationContext).enqueue(workRequest)
        setContent {
            WorkManagerTheme {

            }
        }
    }
}
