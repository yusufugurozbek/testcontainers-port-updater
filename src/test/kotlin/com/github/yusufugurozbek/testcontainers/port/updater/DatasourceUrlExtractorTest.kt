package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_MSSQL_SERVER
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_MYSQL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_POSTGRESQL_1
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL_POSTGRESQL_2
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_MSSQL_SERVER
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_MYSQL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_POSTGRESQL_1
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY_POSTGRESQL_2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DatasourceUrlExtractorTest {

    private val sut = DatasourceUrlExtractor()

    @Test
    fun `extractDataSourceUrl successfully extracts url if present with driver part`() {
        assertEquals(sut.extract(LOG_ENTRY_POSTGRESQL_1), DATASOURCE_URL_POSTGRESQL_1)
    }

    @Test
    fun `extractDataSourceUrl successfully extracts url if present without driver part`() {
        assertEquals(sut.extract(LOG_ENTRY_POSTGRESQL_2), DATASOURCE_URL_POSTGRESQL_2)
    }

    @Test
    fun `extractDataSourceUrl returns null if not found`() {
        assertEquals(sut.extract("someGibberish"), null)
    }

    @Test
    fun `extractDataSourceUrl successfully extracts mssql server url`() {
        assertEquals(sut.extract(LOG_ENTRY_MSSQL_SERVER), DATASOURCE_URL_MSSQL_SERVER)
    }

    @Test
    fun `extractDataSourceUrl successfully extracts mysql url`() {
        assertEquals(sut.extract(LOG_ENTRY_MYSQL), DATASOURCE_URL_MYSQL)
    }
}
