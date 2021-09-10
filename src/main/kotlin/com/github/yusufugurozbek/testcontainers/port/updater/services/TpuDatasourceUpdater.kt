package com.github.yusufugurozbek.testcontainers.port.updater.services

interface TpuDatasourceUpdater {

   /**
    * Process log entry and update data source if updated log entry is present
    */
   fun process(logEntryText: String)
}
