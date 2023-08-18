package com.github.yusufugurozbek.testcontainers.port.updater.constants

object MySqlTestConstants {
    const val MYSQL_LOCAL_DATA_SOURCE_URL = "jdbc:mysql://localhost:12345/test"
    const val MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC = "jdbc:mysql://localhost:12345/test?testcontainers=true"

    const val MYSQL_UPDATED_LOCAL_DATA_SOURCE_URL = "jdbc:mysql://localhost:54321/test"
    const val MYSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC = "jdbc:mysql://localhost:54321/test?testcontainers=true"

    const val MYSQL_LOG_ENTRY = "2022-10-05 19:19:06.886  INFO 16604 --- [" +
            "           main] o.f.c.i.database.base.BaseDatabaseType   : " +
            "Database: jdbc:mysql://localhost:54321/test (MySQL 8.0)"
    const val MYSQL_JSON_LOG_ENTRY = "{\"@timestamp\":\"2021-07-16T15:11:23.4112+02:00\"," +
            "\"@version\":\"1\"," +
            "\"message\":\"Database: jdbc:mysql://localhost:54321/test (MySQL 8.0)\"," +
            "\"logger_name\":\"org.flywaydb.core.internal.database.base.BaseDatabaseType\"," +
            "\"thread_name\":\"main\"," +
            "\"level\":\"INFO\"," +
            "\"level_value\":20000}"
}
