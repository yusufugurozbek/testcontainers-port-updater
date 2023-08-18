package com.github.yusufugurozbek.testcontainers.port.updater.constants

object MongoDbTestConstants {
    const val MONGODB_LOCAL_DATA_SOURCE_URL = "mongodb://localhost:12345/test"
    const val MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC ="mongodb://localhost:12345/test?testcontainers=true"

    const val MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL = "mongodb://localhost:54321/test"
    const val MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC = "mongodb://localhost:54321/test?testcontainers=true"

    const val MONGODB_LOG_ENTRY =
        "00:34:59.184 [main] DEBUG com.github.yusufugurozbek.testcontainersdemo.MongoDbTest" +
                " - Database: mongodb://localhost:54321/test"
    const val MONGODB_JSON_LOG_ENTRY = "{\"@timestamp\":\"2021-07-16T15:11:23.4112+02:00\"," +
            "\"@version\":\"1\"," +
            "\"message\":\"Database: mongodb://localhost:54321/test\"," +
            "\"logger_name\":\"org.flywaydb.core.internal.database.base.BaseDatabaseType\"," +
            "\"thread_name\":\"main\"," +
            "\"level\":\"INFO\"," +
            "\"level_value\":20000}"
}
