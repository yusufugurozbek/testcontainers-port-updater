package com.github.yusufugurozbek.testcontainers.port.updater.api

interface DatasourceUpdater {

    /**
     * Process log entry and update data source if updated log entry is present
     */
    fun update(logEntryText: String)
}
