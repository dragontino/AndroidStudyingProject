package ru.dragontino.androidtestproject.workers

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import ru.dragontino.androidtestproject.R

class DeviceIsChargingWorker(context: Context, params: WorkerParameters) : CoroutineWorker(context, params) {
    private val notificationManager by lazy {
        NotificationManagerCompat.from(applicationContext)
    }


    override suspend fun doWork(): Result {
        val message = applicationContext.getString(R.string.device_is_charging)
        val notification = createNotification(message)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                pushNotification(notification)
            }
        }
        pushNotification(notification)

        return Result.success()
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(NOTIFICATION_ID, createNotification(""))
    }

    private fun createNotification(message: String): Notification {
        val notificationChannel = getNotificationChannel()
        return Notification.Builder(applicationContext, notificationChannel.id)
            .setContentTitle(applicationContext.getString(R.string.app_name))
            .setContentText(message)
            .setColorized(true)
            .setColor(Color.BLUE)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setShowWhen(true)
            .setAutoCancel(true)
            .let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    it.setAllowSystemGeneratedContextualActions(true)
                } else {
                    it
                }
            }
            .build()
    }



    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    private fun pushNotification(notification: Notification) {
        notificationManager.notify(NOTIFICATION_ID, notification)
    }


    private fun getNotificationChannel(): NotificationChannel {
        val channelId = applicationContext.getString(R.string.default_notification_channel_id)
        return notificationManager
            .getNotificationChannel(channelId)
            ?: createNotificationChannel(channelId)
    }


    private fun createNotificationChannel(id: String): NotificationChannel {
        val name = applicationContext.getString(R.string.default_notification_channel_name)
        val channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(channel)
        return channel
    }



    private companion object {
        const val NOTIFICATION_ID = 0
    }
}