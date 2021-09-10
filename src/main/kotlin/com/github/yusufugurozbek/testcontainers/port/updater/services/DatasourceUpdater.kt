package com.github.yusufugurozbek.testcontainers.port.updater.services

interface DatasourceUpdater {

    /**
     * Process log entry and update data source if updated log entry is present
     */
    fun update(logEntryText: String)
}
