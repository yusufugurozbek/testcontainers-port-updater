package com.github.yusufugurozbek.testcontainers.port.updater.constants

object MsSqlTestConstants {
    const val MSSQL_LOCAL_DATA_SOURCE_URL = "jdbc:sqlserver://localhost:12345;connectRetryInterval=10;" +
            "connectRetryCount=1;maxResultBuffer=-1;applicationName=Microsoft JDBC Driver for SQL Server;" +
            "applicationIntent=readwrite;"
    const val MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC = "jdbc:sqlserver://localhost:12345;connectRetryInterval=10;" +
            "connectRetryCount=1;maxResultBuffer=-1;applicationName=Microsoft JDBC Driver for SQL Server;" +
            "applicationIntent=readwrite;testcontainers=true"

    const val MSSQL_UPDATED_LOCAL_DATA_SOURCE_URL = "jdbc:sqlserver://localhost:54321;connectRetryInterval=10;" +
            "connectRetryCount=1;maxResultBuffer=-1;applicationName=Microsoft JDBC Driver for SQL Server;" +
            "applicationIntent=readwrite;"
    const val MSSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC = "jdbc:sqlserver://localhost:54321;connectRetryInterval=10;" +
            "connectRetryCount=1;maxResultBuffer=-1;applicationName=Microsoft JDBC Driver for SQL Server;" +
            "applicationIntent=readwrite;testcontainers=true"

    const val MSSQL_LOG_ENTRY = "2022-09-24 17:33:36.058  INFO 26088 --- [" +
            "           main] o.f.c.i.database.base.BaseDatabaseType   : " +
            "Database: jdbc:sqlserver://localhost:54321;connectRetryInterval=10;" +
            "connectRetryCount=1;maxResultBuffer=-1;" +
            "applicationName=Microsoft JDBC Driver for SQL Server;" +
            "applicationIntent=readwrite; (Microsoft SQL Server 14.0)"
}