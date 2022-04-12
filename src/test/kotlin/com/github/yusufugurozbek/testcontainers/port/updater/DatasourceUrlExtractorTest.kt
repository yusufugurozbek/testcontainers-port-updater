package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.DATASOURCE_URL
import com.github.yusufugurozbek.testcontainers.port.updater.TestConstants.LOG_ENTRY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DatasourceUrlExtractorTest {

    private val sut = DatasourceUrlExtractor()

    @Test
    fun `extractDataSourceUrl successfully extracts url if present`() {
        assertEquals(sut.extract(LOG_ENTRY), DATASOURCE_URL)
    }

    @Test
    fun `extractDataSourceUrl returns null if not found`() {
        assertEquals(sut.extract("someGibberish"), null)
    }
}
