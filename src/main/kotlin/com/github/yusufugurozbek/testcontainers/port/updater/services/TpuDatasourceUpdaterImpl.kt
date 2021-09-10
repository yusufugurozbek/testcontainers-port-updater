package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.github.yusufugurozbek.testcontainers.port.updater.TpuNotifier
import com.github.yusufugurozbek.testcontainers.port.updater.equalsIgnoringPort
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.psi.DbPsiFacade
import com.intellij.database.util.DbImplUtil
import com.intellij.openapi.project.Project

class TpuDatasourceUpdaterImpl(var project: Project) : TpuDatasourceUpdater {
    private var dataSourceUrlExtractor: DataSourceUrlExtractor = DataSourceUrlExtractor()

    override fun process(logEntryText: String) {
        dataSourceUrlExtractor.extractDataSourceUrl(logEntryText)?.let { updatedDataSourceUrl ->
            DbPsiFacade.getInstance(project).dataSources
                .mapNotNull { DbImplUtil.getMaybeLocalDataSource(it) }
                .filter { it.url?.let { updatedDataSourceUrl.equalsIgnoringPort(it) } ?: false }
                .forEach { update(it, updatedDataSourceUrl) }
        }
    }

    private fun update(localDataSource: LocalDataSource, loggedDataSourceUrl: String?) {
        localDataSource.url = loggedDataSourceUrl
        TpuNotifier.notify(project, "Updated data source URL: $loggedDataSourceUrl")
    }
}
