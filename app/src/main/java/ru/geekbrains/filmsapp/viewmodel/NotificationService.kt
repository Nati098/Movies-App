package ru.geekbrains.filmsapp.viewmodel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.geekbrains.filmsapp.R

private const val PUSH_KEY_TITLE = "title"
private const val PUSH_KEY_MESSAGE = "message"
private const val CHANNEL_ID = "channel_id"
private const val NOTIFICATION_ID = 37

class NotificationService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {
            handleData(remoteMessage.data)
        }

        remoteMessage.notification?.let {
            Log.d(this::class.java.canonicalName, "Message Notification Body: ${it.body}")
        }
    }

    private fun handleData(data: Map<String, String>) {
        if (!data[PUSH_KEY_TITLE].isNullOrEmpty() || !data[PUSH_KEY_MESSAGE].isNullOrEmpty()) {
            val notificationBuilder =
                NotificationCompat.Builder(applicationContext, CHANNEL_ID).apply {
                    setSmallIcon(R.mipmap.ic_launcher_foreground)
                    setContentTitle(data[PUSH_KEY_TITLE])
                    setContentText(data[PUSH_KEY_MESSAGE])
                    priority = NotificationCompat.PRIORITY_DEFAULT
                }

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel(notificationManager)
            }
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val name = "NotificationChannel"
        val descriptionText = "NotificationChannelDescription"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }
}