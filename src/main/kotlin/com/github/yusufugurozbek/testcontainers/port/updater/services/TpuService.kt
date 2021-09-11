package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.intellij.execution.ExecutionListener
import com.intellij.execution.ExecutionManager
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessOutputTypes
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key

class TpuService(project: Project) {
    init {
        val datasourceUpdater = project.service<DatasourceUpdater>()
        project.messageBus.connect().subscribe(ExecutionManager.EXECUTION_TOPIC, executionListener(datasourceUpdater))
    }

    private fun executionListener(datasourceUpdater: DatasourceUpdater) = object : ExecutionListener {
        override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
            handler.addProcessListener(processAdapter(datasourceUpdater))
        }
    }

    private fun processAdapter(datasourceUpdater: DatasourceUpdater) = object : ProcessAdapter() {
        override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {

            if (outputType == ProcessOutputTypes.STDOUT && event.text.isNotBlank()) {
                datasourceUpdater.update(event.text)
            }
        }
    }
}
