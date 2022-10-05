package com.github.yusufugurozbek.testcontainers.port.updater

object TestConstants {
    const val DATASOURCE_URL_POSTGRESQL_1 = "jdbc:postgresql://localhost:55001/test"
    const val DATASOURCE_URL_POSTGRESQL_2 = "jdbc:postgresql://localhost:55001/test?loggerLevel=OFF"
    const val DATASOURCE_URL_MSSQL_SERVER = "jdbc:sqlserver://localhost:62510;connectRetryInterval=10;" +
        "connectRetryCount=1;maxResultBuffer=-1;" +
        "applicationName=Microsoft JDBC Driver for SQL Server;applicationIntent=readwrite;"
    const val DATASOURCE_URL_MYSQL = "jdbc:mysql://localhost:59426/test"
    const val LOG_ENTRY_POSTGRESQL_1 = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
        "           main] o.f.c.i.database.base.DatabaseType       : " +
        "Database: jdbc:postgresql://localhost:55001/test (PostgreSQL 10.17)"
    const val LOG_ENTRY_POSTGRESQL_2 = "2021-07-16 14:05:56.360  INFO [my-application,,] 33702 --- [" +
        "           main] o.f.c.i.database.base.DatabaseType       : " +
        "Database: jdbc:postgresql://localhost:55001/test?loggerLevel=OFF"
    const val LOG_ENTRY_MSSQL_SERVER = "2022-09-24 17:33:36.058  INFO 26088 --- [" +
        "           main] o.f.c.i.database.base.BaseDatabaseType   : " +
        "Database: jdbc:sqlserver://localhost:62510;connectRetryInterval=10;" +
        "connectRetryCount=1;maxResultBuffer=-1;" +
        "applicationName=Microsoft JDBC Driver for SQL Server;" +
        "applicationIntent=readwrite; (Microsoft SQL Server 14.0)"
    const val LOG_ENTRY_MYSQL = "2022-10-05 19:19:06.886  INFO 16604 --- [" +
        "           main] o.f.c.i.database.base.BaseDatabaseType   : " +
        "Database: jdbc:mysql://localhost:59426/test (MySQL 8.0)"
}
