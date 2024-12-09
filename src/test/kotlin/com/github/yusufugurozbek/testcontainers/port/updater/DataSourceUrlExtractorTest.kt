package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
import com.intellij.testFramework.UsefulTestCase
import junit.framework.TestCase
import junit.framework.TestCase.*

internal class DataSourceUrlExtractorTest : UsefulTestCase() {

    private val sut = DataSourceUrlExtractor()

    fun `test parts can be successfully extracted from well formed input`() {
        val result = sut.extract("jdbc:postgresql://localhost:11111/test")

        assertEquals("jdbc:postgresql://localhost:11111/test", result?.groups?.get(0)?.value)
        assertEquals("jdbc:postgresql://localhost", result?.groups?.get(1)?.value)
        assertEquals("11111", result?.groups?.get(2)?.value)
        assertEquals("/test", result?.groups?.get(3)?.value)
    }

    fun `test extraction of null value returns null`() {
        assertNull(sut.extract(null))
    }

    fun `test extraction of empty value returns null`() {
        assertNull(sut.extract(""))
    }

}
