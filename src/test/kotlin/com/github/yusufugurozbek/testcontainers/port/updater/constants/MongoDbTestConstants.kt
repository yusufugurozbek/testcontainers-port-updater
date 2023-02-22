package com.github.yusufugurozbek.testcontainers.port.updater.constants

object MongoDbTestConstants {
    const val MONGODB_LOCAL_DATA_SOURCE_URL = "mongodb://localhost:12345/test"
    const val MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC ="mongodb://localhost:12345/test?testcontainers=true"

    const val MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL = "mongodb://localhost:54321/test"
    const val MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC = "mongodb://localhost:54321/test?testcontainers=true"

    const val MONGODB_LOG_ENTRY =
        "00:34:59.184 [main] DEBUG com.github.yusufugurozbek.testcontainersdemo.MongoDbTest" +
                " - Database: mongodb://localhost:54321/test"
}
