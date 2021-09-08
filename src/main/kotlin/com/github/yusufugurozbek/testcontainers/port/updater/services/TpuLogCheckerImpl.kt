package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.github.yusufugurozbek.testcontainers.port.updater.TpuBundle
import com.github.yusufugurozbek.testcontainers.port.updater.TpuNotifier
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.database.psi.DbPsiFacade
import com.intellij.database.util.DbImplUtil
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessOutputTypes
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import java.util.regex.Matcher
import java.util.regex.Pattern

class TpuLogCheckerImpl(var project: Project) : TpuLogChecker {
    private var logCheckerPattern: Pattern
    private var portRegex: Regex
    private var portPlaceholder: String

    init {
        val logCheckerRegex = TpuBundle.message("logCheckerRegex")
        logCheckerPattern = Pattern.compile(logCheckerRegex, Pattern.CASE_INSENSITIVE)
        portRegex = Regex(TpuBundle.message("portRegex"))
        portPlaceholder = TpuBundle.message("portPlaceholder")
    }

    override fun checkLogs(event: ProcessEvent, outputType: Key<*>) {
        if (outputType != ProcessOutputTypes.STDOUT && event.text.isNullOrBlank()) {
            return
        }

        val logCheckerMatcher: Matcher = logCheckerPattern.matcher(event.text)
        if (logCheckerMatcher.find()) {
            val loggedDataSourceUrl = logCheckerMatcher.group(1)
            val loggedDataSourceUrlWithPortPlaceholder = getDataSourceUrlWithPortPlaceholder(loggedDataSourceUrl)

            DbPsiFacade.getInstance(project).dataSources.forEach { dataSource ->
                val localDataSource = DbImplUtil.getMaybeLocalDataSource(dataSource) ?: return@forEach
                if (hasDataSourceSameUrl(localDataSource, loggedDataSourceUrlWithPortPlaceholder)) {
                    TpuNotifier.notify(project, "Old data source URL: ${localDataSource.url}")
                    localDataSource.url = loggedDataSourceUrl
                    TpuNotifier.notify(project, "Updated data source URL: $loggedDataSourceUrl")
                }
            }
        }
    }

    private fun hasDataSourceSameUrl(dataSource: LocalDataSource, dataSourceUrlWithPortPlaceholder: String) =
        dataSource.url?.let(::getDataSourceUrlWithPortPlaceholder) == dataSourceUrlWithPortPlaceholder

    private fun getDataSourceUrlWithPortPlaceholder(dataSourceUrl: String) =
        dataSourceUrl.replace(portRegex, portPlaceholder)
}
