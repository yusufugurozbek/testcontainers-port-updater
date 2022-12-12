package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.DatasourceUrlExtractor
import com.github.yusufugurozbek.testcontainers.port.updater.api.DatasourceUpdater
import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuNotifier
import com.github.yusufugurozbek.testcontainers.port.updater.common.equalsIgnoringPort
import com.github.yusufugurozbek.testcontainers.port.updater.common.hasPort
import com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState
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
                .filter { it.url != newUrl }
                .filter { it.url?.hasPort() == true && newUrl.hasPort() }
                .filter { it.url?.equalsIgnoringPort(newUrl) ?: false }
                .forEach { update(it, newUrl) }
        }
    }

    private fun update(localDataSource: LocalDataSource, newUrl: String) {
        localDataSource.url = newUrl
        if (TpuSettingsState.instance.isNotificationsEnabled) {
            TpuNotifier.notify(project, "Updated data source URL: $newUrl")
        }
    }
}
