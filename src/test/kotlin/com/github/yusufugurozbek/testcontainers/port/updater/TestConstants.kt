package com.github.yusufugurozbek.testcontainers.port.updater

object TestConstants {
    const val DATASOURCE_URL = "jdbc:postgresql://localhost:55001/test"
    const val LOG_ENTRY =
        "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
            "           main] o.f.c.i.database.base.DatabaseType       : " +
            "Database: jdbc:postgresql://localhost:55001/test (PostgreSQL 10.17)"
}
