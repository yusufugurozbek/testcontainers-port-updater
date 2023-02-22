package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState
import com.intellij.database.dataSource.LocalDataSource
import com.intellij.openapi.components.service
import com.intellij.testFramework.fixtures.BasePlatformTestCase

abstract class DataSourceUpdaterImplBaseTestCase : BasePlatformTestCase() {
    internal lateinit var sut: DataSourceUpdaterImpl
    internal lateinit var allLocalDataSources: List<LocalDataSource>

    override fun setUp() {
        super.setUp()
        sut = DataSourceUpdaterImpl(project)
        project.service<TpuSettingsState>().isNotificationsEnabled = false

        allLocalDataSources = listOf(
            createLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL", POSTGRESQL_LOCAL_DATA_SOURCE_URL),
            createLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC", POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC),
            createLocalDataSource(
                "POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER",
                POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
            ),
            createLocalDataSource(
                "POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC",
                POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
            ),
            createLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL", MSSQL_LOCAL_DATA_SOURCE_URL),
            createLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC", MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC),
            createLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL", MYSQL_LOCAL_DATA_SOURCE_URL),
            createLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC", MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC),
            createLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL", MONGODB_LOCAL_DATA_SOURCE_URL),
            createLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC", MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC)
        )
    }

    internal fun getLocalDataSource(name: String): LocalDataSource {
        return allLocalDataSources.first { it.name == name }
    }

    private fun createLocalDataSource(name: String, url: String): LocalDataSource {
        return LocalDataSource.create(
            name,
            "driver",
            url,
            "username"
        )
    }
}
