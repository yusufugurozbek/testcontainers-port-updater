package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.intellij.execution.ExecutionListener
import com.intellij.execution.ExecutionManager
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key

class TpuService(project: Project) {
    init {
        val logChecker = project.service<TpuLogChecker>()
        project.messageBus.connect().subscribe(ExecutionManager.EXECUTION_TOPIC, executionListener(logChecker))
    }

    private fun executionListener(logChecker: TpuLogChecker) = object : ExecutionListener {
        override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
            handler.addProcessListener(processAdapter(logChecker))
        }
    }

    private fun processAdapter(logChecker: TpuLogChecker) = object : ProcessAdapter() {
        override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
            logChecker.checkLogs(event, outputType)
        }
    }
}
