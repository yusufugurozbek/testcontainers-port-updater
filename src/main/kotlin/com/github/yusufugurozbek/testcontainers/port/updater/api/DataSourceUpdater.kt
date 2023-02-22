package com.github.yusufugurozbek.testcontainers.port.updater.api

import com.intellij.database.dataSource.LocalDataSource

interface DataSourceUpdater {

    /**
     * Process log entry and update local data sources if log entry has data source URL
     */
    fun update(localDataSources: List<LocalDataSource>, logEntryText: String)
}
