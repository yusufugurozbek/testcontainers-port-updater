package com.github.yusufugurozbek.testcontainers.port.updater.common

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

class TpuNotifier private constructor() {
    companion object {
        fun notify(project: Project, message: String) {
            NotificationGroupManager.getInstance().getNotificationGroup(TpuBundle.message("name"))
                .createNotification(message, NotificationType.INFORMATION)
                .notify(project)
        }
    }
}
