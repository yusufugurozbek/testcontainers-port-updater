package com.github.yusufugurozbek.testcontainers.port.updater.services

import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DataSourceUrlExtractorTest {

    private val sut = DataSourceUrlExtractor()

    @Test
    fun `extractDataSourceUrl successfullyExtractsUrlIfPresent`() {
        assertEquals(sut.extractDataSourceUrl(LOG_ENTRY), DATASOURCE_URL)
    }

    @Test
    fun `extractDataSourceUrl returnsNullIfNotFound`() {
        assertEquals(sut.extractDataSourceUrl("someGibberish"), null)
    }
}
