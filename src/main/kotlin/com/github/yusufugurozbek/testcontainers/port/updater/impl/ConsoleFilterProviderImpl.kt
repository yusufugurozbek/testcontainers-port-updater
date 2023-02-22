package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.psi.DbPsiFacade
import com.intellij.database.util.DbImplUtil
import com.intellij.execution.filters.ConsoleFilterProvider
import com.intellij.execution.filters.Filter
import com.intellij.openapi.project.Project

class ConsoleFilterProviderImpl : ConsoleFilterProvider {
    override fun getDefaultFilters(project: Project): Array<Filter> {
        val dataSources: List<LocalDataSource> = DbPsiFacade.getInstance(project).dataSources
            .mapNotNull { DbImplUtil.getMaybeLocalDataSource(it) }

        return arrayOf(DataSourceFilter(project, dataSources))
    }
}
