package com.github.yusufugurozbek.testcontainers.port.updater

import com.intellij.database.psi.DbPsiFacade
import com.intellij.database.util.DbImplUtil
import com.intellij.execution.ExecutionListener
import com.intellij.execution.ExecutionManager
import com.intellij.execution.process.ProcessAdapter
import com.intellij.execution.process.ProcessEvent
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessOutputTypes
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import java.net.URI
import java.util.regex.Matcher
import java.util.regex.Pattern

class TpuService(project: Project) {

    init {
        project.messageBus.connect().subscribe(
            ExecutionManager.EXECUTION_TOPIC,
            object : ExecutionListener {
                override fun processStarted(executorId: String, env: ExecutionEnvironment, handler: ProcessHandler) {
                    handler.addProcessListener(object : ProcessAdapter() {
                        override fun onTextAvailable(event: ProcessEvent, outputType: Key<*>) {
                            checkLogs(event, outputType, project)
                        }
                    })
                }
            }
        )
    }

    private fun checkLogs(event: ProcessEvent, outputType: Key<*>, project: Project) {
        val matcher: Matcher = getMatcher(event)
        if (outputType == ProcessOutputTypes.STDOUT && event.text.isNotEmpty() && matcher.find()) {
            val newJdbcUrl = matcher.group(1)
            val jdbcLength = "jdbc:".length
            val newJdbcUri: URI = URI.create(newJdbcUrl.substring(jdbcLength))

            DbPsiFacade.getInstance(project).dataSources.forEach { dataSource ->
                val localDataSource = DbImplUtil.getMaybeLocalDataSource(dataSource)
                val localDataSourceJdbcUri = URI.create(localDataSource?.url!!.substring(jdbcLength))
                if (isTestcontainersDataSource(localDataSourceJdbcUri, newJdbcUri)) {
                    localDataSource.url = newJdbcUrl
                    TpuNotifier.notify(
                        project,
                        "Testcontainers data source port is updated " +
                            "from ${localDataSourceJdbcUri.port} to ${newJdbcUri.port}"
                    )
                }
            }
        }
    }

    private fun isTestcontainersDataSource(localDataSourceJdbcUri: URI, newJdbcUri: URI) =
        localDataSourceJdbcUri.scheme == newJdbcUri.scheme &&
            localDataSourceJdbcUri.host == newJdbcUri.host &&
            localDataSourceJdbcUri.scheme == newJdbcUri.scheme &&
            localDataSourceJdbcUri.path == newJdbcUri.path

    private fun getMatcher(event: ProcessEvent): Matcher {
        val pattern: Pattern = Pattern.compile(" Database: (.*?) \\((.*?)\\)")
        return pattern.matcher(event.text)
    }
}
