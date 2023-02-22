package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.api.DataSourceUpdater
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.execution.filters.Filter
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project

class DataSourceFilter(private val project: Project, private val localDataSources: List<LocalDataSource>) : Filter {
    override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
        val dataSourceUpdater = project.service<DataSourceUpdater>()
        if (line.isNotBlank() and localDataSources.isNotEmpty()) {
            dataSourceUpdater.update(localDataSources, line)
        }
        return null
    }
}
