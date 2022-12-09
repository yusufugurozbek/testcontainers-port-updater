package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_MSSQL_SERVER
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_MYSQL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_POSTGRESQL_1
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_POSTGRESQL_2
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_MSSQL_SERVER
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_MYSQL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_POSTGRESQL_1
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_POSTGRESQL_2
import com.intellij.testFramework.fixtures.BasePlatformTestCase

internal class DatasourceUrlExtractorTest : BasePlatformTestCase() {

    private val sut = DatasourceUrlExtractor()

    fun `test extractDataSourceUrl successfully extracts url if present with driver part`() {
        assertEquals(sut.extract(LOG_ENTRY_POSTGRESQL_1), DATASOURCE_URL_POSTGRESQL_1)
    }

    fun `test extractDataSourceUrl successfully extracts url if present without driver part`() {
        assertEquals(sut.extract(LOG_ENTRY_POSTGRESQL_2), DATASOURCE_URL_POSTGRESQL_2)
    }

    fun `test extractDataSourceUrl returns null if not found`() {
        assertEquals(sut.extract("someGibberish"), null)
    }

    fun `test extractDataSourceUrl successfully extracts mssql server url`() {
        assertEquals(sut.extract(LOG_ENTRY_MSSQL_SERVER), DATASOURCE_URL_MSSQL_SERVER)
    }

    fun `test extractDataSourceUrl successfully extracts mysql url`() {
        assertEquals(sut.extract(LOG_ENTRY_MYSQL), DATASOURCE_URL_MYSQL)
    }
}
