package com.github.yusufugurozbek.testcontainers.port.updater.constants

object PostgreSqlTestConstants {
    const val POSTGRESQL_LOCAL_DATA_SOURCE_URL = "jdbc:postgresql://localhost:12345/test"
    const val POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC = "jdbc:postgresql://localhost:12345/test?testcontainers=true"
    const val POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER = "jdbc:postgresql://localhost:12345/test?loggerLevel=OFF"
    const val POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC =
        "jdbc:postgresql://localhost:12345/test?loggerLevel=OFF&testcontainers=true"

    const val POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL = "jdbc:postgresql://localhost:54321/test"
    const val POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC =
        "jdbc:postgresql://localhost:54321/test?testcontainers=true"
    const val POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER =
        "jdbc:postgresql://localhost:54321/test?loggerLevel=OFF"
    const val POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC =
        "jdbc:postgresql://localhost:54321/test?loggerLevel=OFF&testcontainers=true"

    const val POSTGRESQL_LOG_ENTRY = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
            "           main] o.f.c.i.database.base.DatabaseType       : " +
            "Database: jdbc:postgresql://localhost:54321/test (PostgreSQL 10.17)"
    const val POSTGRESQL_LOG_ENTRY_WITH_PARAMETER = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
            "           main] o.f.c.i.database.base.DatabaseType       : " +
            "Database: jdbc:postgresql://localhost:54321/test?loggerLevel=OFF"
    const val POSTGRESQL_JSON_LOG_ENTRY = "{\"@timestamp\":\"2021-07-16T15:11:23.4112+02:00\"," +
            "\"@version\":\"1\"," +
            "\"message\":\"Database: jdbc:postgresql://localhost:54321/test (PostgreSQL 10.17)\"," +
            "\"logger_name\":\"org.flywaydb.core.internal.database.base.BaseDatabaseType\"," +
            "\"thread_name\":\"main\"," +
            "\"level\":\"INFO\"," +
            "\"level_value\":20000}"
    const val POSTGRESQL_JSON_LOG_ENTRY_WITH_PARAMETER = "{\"@timestamp\":\"2021-07-16T14:05:56.4112+02:00\"," +
            "\"@version\":\"1\"," +
            "\"message\":\"Database: jdbc:postgresql://localhost:54321/test?loggerLevel=OFF\"," +
            "\"logger_name\":\"org.flywaydb.core.internal.database.base.BaseDatabaseType\"," +
            "\"thread_name\":\"main\"," +
            "\"level\":\"INFO\"," +
            "\"level_value\":20000}"
}
