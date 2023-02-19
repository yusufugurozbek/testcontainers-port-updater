package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.api.DataSourceUpdater
import com.intellij.execution.filters.Filter
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

class DataSourceFilter(private val project: Project) : Filter {
    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        val dataSourceUpdater = project.service<DataSourceUpdater>()
        if (line.isNotBlank()) {
            dataSourceUpdater.update(line)
        }
        return null
    }
}
