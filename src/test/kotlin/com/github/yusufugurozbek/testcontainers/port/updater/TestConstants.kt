package com.github.yusufugurozbek.testcontainers.port.updater

object TestConstants {
    const val DATASOURCE_URL_1 = "jdbc:postgresql://localhost:55001/test"
    const val DATASOURCE_URL_2 = "jdbc:postgresql://localhost:55001/test?loggerLevel=OFF"
    const val LOG_ENTRY_1 = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
            "           main] o.f.c.i.database.base.DatabaseType       : " +
            "Database: jdbc:postgresql://localhost:55001/test (PostgreSQL 10.17)"
    const val LOG_ENTRY_2 = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
            "           main] o.f.c.i.database.base.DatabaseType       : " +
            "Database: jdbc:postgresql://localhost:55001/test?loggerLevel=OFF"
}
