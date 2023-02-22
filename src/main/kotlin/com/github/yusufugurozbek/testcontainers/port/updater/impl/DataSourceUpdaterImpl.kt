package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.DataSourceUrl
import com.github.yusufugurozbek.testcontainers.port.updater.DataSourceUrlExtractor
import com.github.yusufugurozbek.testcontainers.port.updater.api.DataSourceUpdater
import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuNotifier
import com.github.yusufugurozbek.testcontainers.port.updater.equalsIgnoringPort
import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.openapi.project.Project

class DataSourceUpdaterImpl(private var project: Project) : DataSourceUpdater {

    private var urlExtractor: DataSourceUrlExtractor = DataSourceUrlExtractor()

    override fun update(localDataSources: List<LocalDataSource>, logEntryText: String) {
        val splitLogEntry = logEntryText.split(TpuSettingsState.instance.logEntryPrefix)
        if (splitLogEntry.size == 2) {
            urlExtractor.extract(splitLogEntry[1])?.let { logEntryDataSourceUrl ->
                localDataSources
                    .filter { it.url != logEntryDataSourceUrl.toString() }
                    .forEach { update(it, logEntryDataSourceUrl) }
            }
        }
    }

    private fun update(localDataSource: LocalDataSource, logEntryDataSourceUrl: DataSourceUrl) {
        val localDataSourceUrl = urlExtractor.toDataSourceUrl(localDataSource) ?: return

        if (localDataSourceUrl.port == logEntryDataSourceUrl.port) {
            return
        }

        val isUpdatable = when (TpuSettingsState.instance.matchMode) {
            MatchMode.EXACT -> localDataSourceUrl.equalsIgnoringPort(logEntryDataSourceUrl)
            MatchMode.EVERYTHING -> localDataSourceUrl.beforePort == logEntryDataSourceUrl.beforePort
            MatchMode.WITH_TESTCONTAINERS_PARAMETER ->
                localDataSourceUrl.toString().contains("testcontainers=true") and
                        (localDataSourceUrl.beforePort == logEntryDataSourceUrl.beforePort)
        }

        if (isUpdatable) {
            val newUrl = localDataSourceUrl.toString().replace(localDataSourceUrl.port, logEntryDataSourceUrl.port)
            localDataSource.url = newUrl
            if (TpuSettingsState.instance.isNotificationsEnabled) {
                TpuNotifier.notify(project, "Updated data source URL: $newUrl")
            }
        }
    }
}
