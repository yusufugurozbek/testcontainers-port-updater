package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.github.yusufugurozbek.testcontainers.port.updater.TpuNotifier
import com.github.yusufugurozbek.testcontainers.port.updater.equalsIgnoringPort
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.psi.DbPsiFacade
import com.intellij.database.util.DbImplUtil
import com.intellij.openapi.project.Project

class DatasourceUpdaterImpl(var project: Project) : DatasourceUpdater {

    private var urlExtractor: DatasourceUrlExtractor = DatasourceUrlExtractor()

    override fun update(logEntryText: String) {
        urlExtractor.extract(logEntryText)?.let { newUrl ->
            DbPsiFacade.getInstance(project).dataSources
                .mapNotNull { DbImplUtil.getMaybeLocalDataSource(it) }
                .filter { it.url?.equalsIgnoringPort(newUrl) ?: false }
                .forEach { update(it, newUrl) }
        }
    }

    private fun update(localDataSource: LocalDataSource, newUrl: String) {
        localDataSource.url = newUrl
        TpuNotifier.notify(project, "Updated data source URL: $newUrl")
    }
}
