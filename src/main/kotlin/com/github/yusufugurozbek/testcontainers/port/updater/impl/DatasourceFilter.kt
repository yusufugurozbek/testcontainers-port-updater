package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.api.DatasourceUpdater
import com.intellij.execution.filters.Filter
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

class DatasourceFilter(private val project: Project) : Filter {
    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        val datasourceUpdater = project.service<DatasourceUpdater>()
        if (line.isNotBlank()) {
            datasourceUpdater.update(line)
        }
        return null
    }
}
