package com.github.yusufugurozbek.testcontainers.port.updater

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationGroup
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

private val NOTIFICATION_GROUP =
    NotificationGroup(TpuBundle.message("name"), NotificationDisplayType.BALLOON, true)

class TpuNotifier private constructor() {
    companion object {
        fun notify(project: Project, message: String) {
            val notification = NOTIFICATION_GROUP.createNotification(
                TpuBundle.message("name"),
                message,
                NotificationType.INFORMATION
            )
            notification.notify(project)
        }
    }
}
