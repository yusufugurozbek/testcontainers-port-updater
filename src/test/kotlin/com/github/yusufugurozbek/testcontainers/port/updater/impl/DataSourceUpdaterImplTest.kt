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
import com.github.yusufugurozbek.testcontainers.port.updater.constants.TestConstants.DUMMY_LOG_ENTRY
import com.github.yusufugurozbek.testcontainers.port.updater.constants.TestConstants.DUMMY_LOG_ENTRY_WITHOUT_PREFIX
import com.github.yusufugurozbek.testcontainers.port.updater.constants.TestConstants.WITHOUT_PORT_LOG_ENTRY

internal class DataSourceUpdaterImplTest : DataSourceUpdaterImplBaseTestCase() {

    fun `test DataSourceUpdaterImpl update method without port number log entry updates nothing`() {
        // given + when
        sut.update(allLocalDataSources, WITHOUT_PORT_LOG_ENTRY)

        // then
        assertIsNothingUpdated()
    }

    fun `test DataSourceUpdaterImpl update method with dummy log entry updates nothing`() {
        // given + when
        sut.update(allLocalDataSources, DUMMY_LOG_ENTRY)

        // then
        assertIsNothingUpdated()
    }

    fun `test DataSourceUpdaterImpl update method with dummy log entry without prefix updates nothing`() {
        // given + when
        sut.update(allLocalDataSources, DUMMY_LOG_ENTRY_WITHOUT_PREFIX)

        // then
        assertIsNothingUpdated()
    }

    private fun assertIsNothingUpdated() {
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
            MONGODB_LOCAL_DATA_SOURCE_URL_WITH_TC
        )
    }
}