package com.example.catagenttracker.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.catagenttracker.MainActivity

class RouteTrackingWorker(
    context: Context,
    parameters: WorkerParameters
) : CoroutineWorker(context, parameters) {

    private fun getPendingIntent(): PendingIntent {
        val flag = if (
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
        ) {
            FLAG_IMMUTABLE
        } else {
            0
        }
        return PendingIntent.getActivity(
            applicationContext, 0, Intent(
                applicationContext,
                MainActivity::class.java
            ), flag
        )
    }

    private fun createNotificationChannel(): String = if (
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
    ) {
        val newChannelId = "CatDispatch"
        val channelName = "Cat Dispatch Tracking"
        val channel = NotificationChannel(
            newChannelId, channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        val service = requireNotNull(
            ContextCompat.getSystemService(
                applicationContext,
                NotificationManager::class.java
            )
        )
        service.createNotificationChannel(channel)
        newChannelId
    } else { "" }

    companion object {
        private const val NOTIFICATION_ID = 0xCA7
        private const val INITIAL_SECONDS_LEFT = 10
        const val DATA_KEY_CAT_AGENT_ID = "AgentId"
        const val DATA_KEY_SECONDS_LEFT = "SecondsLeft"
    }
}
