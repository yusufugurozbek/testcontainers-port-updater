package com.github.yusufugurozbek.testcontainers.port.updater.common

import com.intellij.testFramework.fixtures.BasePlatformTestCase

internal class StringUtilsKtTest : BasePlatformTestCase() {

    fun `test equalsIgnoringPort successfully detects equality`() {
        val jdbcStringA = "jdbc:postgresql://localhost:55001/test"
        val jdbcStringB = "jdbc:postgresql://localhost:55035/test"
        assertTrue(jdbcStringA.equalsIgnoringPort(jdbcStringB))
    }

    fun `test equalsIgnoringPort successfully detects equality with IP addresses as hostnames`() {
        val jdbcStringA = "jdbc:postgresql://127.0.0.1:55001/test"
        val jdbcStringB = "jdbc:postgresql://127.0.0.1:55002/test"
        assertTrue(jdbcStringA.equalsIgnoringPort(jdbcStringB))
    }

    fun `test equalsIgnoringPort successfully detects inequality`() {
        val jdbcStringA = "jdbc:postgresql://localhost:55001/test"
        val jdbcStringB = "jdbc:sqlserver://localhost:55001/test"
        assertFalse(jdbcStringA.equalsIgnoringPort(jdbcStringB))
    }

    fun `test hasPort successfully detects the port`() {
        val jdbcString = "jdbc:postgresql://localhost:55001/test"
        assertTrue(jdbcString.hasPort())
    }

    fun `test hasPort successfully detects no port`() {
        val jdbcString = "jdbc:postgresql://localhost/test"
        assertFalse(jdbcString.hasPort())
    }
}
