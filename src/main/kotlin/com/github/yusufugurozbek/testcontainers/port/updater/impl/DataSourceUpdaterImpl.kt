package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.DataSourceUrl
import com.github.yusufugurozbek.testcontainers.port.updater.DataSourceUrlExtractor
import com.github.yusufugurozbek.testcontainers.port.updater.api.DataSourceUpdater
import com.github.yusufugurozbek.testcontainers.port.updater.common.TpuNotifier
import com.github.yusufugurozbek.testcontainers.port.updater.equalsIgnoringPort
import com.github.yusufugurozbek.testcontainers.port.updater.settings.LoggingFormat
import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.openapi.components.service
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

class DataSourceUpdaterImpl(private var project: Project) : DataSourceUpdater {

    private var urlExtractor: DataSourceUrlExtractor = DataSourceUrlExtractor()
    private var settingsState: TpuSettingsState = project.service()

    override fun update(localDataSources: List<LocalDataSource>, logEntryText: String) {
        val logEntry = getLogMessage(logEntryText)

        val splitLogEntry = logEntry.split(settingsState.logEntryPrefix)
        if (splitLogEntry.size == 2) {
            urlExtractor.extract(splitLogEntry[1])?.let { logEntryDataSourceUrl ->
                localDataSources
                    .filter { it.url != logEntryDataSourceUrl.toString() }
                    .forEach { update(it, logEntryDataSourceUrl) }
            }
        }
    }

    private fun getLogMessage(logEntryText: String): String {
        if (settingsState.loggingFormat == LoggingFormat.JSON && logEntryText.contains(settingsState.logEntryPrefix)) {
            try {
                return Json.parseToJsonElement(logEntryText).jsonObject["message"]!!.jsonPrimitive.content
            } catch (e: Exception) {
                thisLogger().warn("JSON log message cannot be extracted", e)
            }
        }
        return logEntryText
    }

    private fun update(localDataSource: LocalDataSource, logEntryDataSourceUrl: DataSourceUrl) {
        val localDataSourceUrl = urlExtractor.toDataSourceUrl(localDataSource) ?: return

        if (localDataSourceUrl.port == logEntryDataSourceUrl.port) {
            return
        }

        val isUpdatable = when (settingsState.matchMode) {
            MatchMode.EXACT -> localDataSourceUrl.equalsIgnoringPort(logEntryDataSourceUrl)
            MatchMode.EVERYTHING -> localDataSourceUrl.beforePort == logEntryDataSourceUrl.beforePort
            MatchMode.WITH_TESTCONTAINERS_PARAMETER ->
                localDataSourceUrl.toString().contains("testcontainers=true") and
                        (localDataSourceUrl.beforePort == logEntryDataSourceUrl.beforePort)
        }

        if (isUpdatable) {
            val newUrl = localDataSourceUrl.toString().replace(localDataSourceUrl.port, logEntryDataSourceUrl.port)
            localDataSource.url = newUrl
            if (settingsState.isNotificationsEnabled) {
                TpuNotifier.notify(project, "Updated data source URL: $newUrl")
            }
        }
    }
}
