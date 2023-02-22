package com.github.yusufugurozbek.testcontainers.port.updater

import com.intellij.testFramework.UsefulTestCase

internal class DataSourceUrlTest : UsefulTestCase() {

    fun `test DataSourceUrl equals successfully returns true`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "port", "afterPort")

        assertTrue(dataSourceUrlA == dataSourceUrlB)
    }

    fun `test DataSourceUrl equals successfully returns false`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "port", null)

        assertFalse(dataSourceUrlA == dataSourceUrlB)
    }

    fun `test DataSourceUrl toString successfully returns full URL`() {
        val dataSourceUrl = DataSourceUrl("beforePort", "port", "?afterPort")

        assertEquals(dataSourceUrl.toString(), "beforePort:port?afterPort")
    }
}
