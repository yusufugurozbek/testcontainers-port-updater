package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.intellij.execution.filters.ConsoleFilterProvider
import com.intellij.execution.filters.Filter
import com.intellij.openapi.project.Project

class ConsoleFilterProviderImpl : ConsoleFilterProvider {
    override fun getDefaultFilters(project: Project): Array<Filter> {
        return arrayOf(DatasourceFilter(project))
    }
}
