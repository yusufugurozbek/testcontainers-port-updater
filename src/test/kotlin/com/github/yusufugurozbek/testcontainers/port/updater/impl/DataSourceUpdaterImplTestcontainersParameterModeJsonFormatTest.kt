package com.github.yusufugurozbek.testcontainers.port.updater.impl

import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_JSON_LOG_ENTRY
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MongoDbTestConstants.MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_JSON_LOG_ENTRY
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MsSqlTestConstants.MSSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_JSON_LOG_ENTRY
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.MySqlTestConstants.MYSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_JSON_LOG_ENTRY
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_JSON_LOG_ENTRY_WITH_PARAMETER
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
import com.github.yusufugurozbek.testcontainers.port.updater.constants.PostgreSqlTestConstants.POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
import com.github.yusufugurozbek.testcontainers.port.updater.settings.LoggingFormat
import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.github.yusufugurozbek.testcontainers.port.updater.settings.TpuSettingsState
import com.intellij.openapi.components.service

internal class DataSourceUpdaterImplTestcontainersParameterModeJsonFormatTest : DataSourceUpdaterImplBaseTestCase() {

    override fun setUp() {
        super.setUp()
        project.service<TpuSettingsState>().matchMode = MatchMode.WITH_TESTCONTAINERS_PARAMETER
        project.service<TpuSettingsState>().loggingFormat = LoggingFormat.JSON
    }

    fun `test DataSourceUpdaterImpl update method updates only PostgreSQL for JSON`() {
        // given + when
        sut.update(allLocalDataSources, POSTGRESQL_JSON_LOG_ENTRY)

        // then
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC").url,
            POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL").url,
            MSSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL").url,
            MYSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL").url,
            MONGODB_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }

    fun `test DataSourceUpdaterImpl update method updates only PostgreSQL with parameter for JSON`() {
        // given + when
        sut.update(allLocalDataSources, POSTGRESQL_JSON_LOG_ENTRY_WITH_PARAMETER)

        // then
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC").url,
            POSTGRESQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL").url,
            MSSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL").url,
            MYSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL").url,
            MONGODB_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }

    fun `test DataSourceUpdaterImpl update method updates only MongoDB for JSON`() {
        // given + when
        sut.update(allLocalDataSources, MONGODB_JSON_LOG_ENTRY)

        // then
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL").url,
            MSSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL").url,
            MYSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL").url,
            MONGODB_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MONGODB_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }

    fun `test DataSourceUpdaterImpl update method updates only MySql with parameter for JSON`() {
        // given + when
        sut.update(allLocalDataSources, MYSQL_JSON_LOG_ENTRY)

        // then
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL").url,
            MSSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL").url,
            MYSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MYSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL").url,
            MONGODB_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }

    fun `test DataSourceUpdaterImpl update method updates only MSSQL with parameter for JSON`() {
        // given + when
        sut.update(allLocalDataSources, MSSQL_JSON_LOG_ENTRY)

        // then
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER
        )
        assertEquals(
            getLocalDataSource("POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC").url,
            POSTGRESQL_LOCAL_DATA_SOURCE_URL_WITH_PARAMETER_AND_TC
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL").url,
            MSSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MSSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MSSQL_UPDATED_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL").url,
            MYSQL_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MYSQL_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL").url,
            MONGODB_LOCAL_DATA_SOURCE_URL
        )
        assertEquals(
            getLocalDataSource("MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC").url,
            MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }
}