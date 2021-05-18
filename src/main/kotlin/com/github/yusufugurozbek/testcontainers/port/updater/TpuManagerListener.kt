package com.github.yusufugurozbek.testcontainers.port.updater

import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManagerListener

internal class TpuManagerListener : ProjectManagerListener {

    override fun projectOpened(project: Project) {
        project.service<TpuService>()
    }
}
