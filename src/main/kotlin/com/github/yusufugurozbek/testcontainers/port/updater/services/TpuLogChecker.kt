package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.intellij.execution.process.ProcessEvent
import com.intellij.openapi.util.Key

interface TpuLogChecker {
    fun checkLogs(event: ProcessEvent, outputType: Key<*>)
}
