package com.github.yusufugurozbek.testcontainers.port.updater

import com.github.yusufugurozbek.testcontainers.port.updater.settings.MatchMode
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

        assertEquals("beforePort:port?afterPort", dataSourceUrl.toString())
    }

    fun `test datasource from string successfully returns result`() {
        val result = DataSourceUrl.from("jdbc:postgresql://localhost:11111/test")!!

        assertEquals("jdbc:postgresql://localhost", result.beforePort)
        assertEquals("11111", result.port)
        assertEquals("/test", result.afterPort)
    }

    fun `test datasource from string returns null on null input`() {
        val result = DataSourceUrl.from(null)

        assertNull(result)
    }

    fun `test datasource from string returns null on empty input`() {
        val result = DataSourceUrl.from("")

        assertNull(result)
    }

    fun `test matchMode EXACT successfully returns true for matching urls`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "port", "afterPort")

        assertTrue(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EXACT))
    }

    fun `test matchMode EXACT returns false for not matching beforePort`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePortNoMatch", "port", "afterPort")

        assertFalse(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EXACT))
    }

    fun `test matchMode EXACT returns true if everything but port matches`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "portNoMatch", "afterPort")

        assertTrue(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EXACT))
    }

    fun `test matchMode EXACT returns false for not matching afterPort`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "port", "afterPortNoMatch")

        assertFalse(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EXACT))
    }

    fun `test matchMode EVERYTHING successfully returns true if beforePort matches`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "portA", "afterPortA")
        val dataSourceUrlB = DataSourceUrl("beforePort", "portB", "afterPortB")

        assertTrue(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EVERYTHING))
    }

    fun `test matchMode EVERYTHING returns false for not matching beforePort`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePortNoMatch", "port", "afterPort")

        assertFalse(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.EVERYTHING))
    }

    fun `test matchMode WITH_TESTCONTAINERS_PARAMETER successfully returns true for matching beforePort and afterPort containing testcontainers=true`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "dontCareA", "testcontainers=true")
        val dataSourceUrlB = DataSourceUrl("beforePort", "dontCareB", "dontCareC")

        assertTrue(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.WITH_TESTCONTAINERS_PARAMETER))
    }

    fun `test matchMode WITH_TESTCONTAINERS_PARAMETER returns false for missing afterPort containing testcontainers=true`() {
        val dataSourceUrlA = DataSourceUrl("beforePort", "port", "afterPort")
        val dataSourceUrlB = DataSourceUrl("beforePort", "port", "afterPort")

        assertFalse(dataSourceUrlA.matches(dataSourceUrlB, MatchMode.WITH_TESTCONTAINERS_PARAMETER))
    }
}
