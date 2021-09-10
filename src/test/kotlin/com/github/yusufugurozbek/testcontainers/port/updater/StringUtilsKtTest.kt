package com.github.yusufugurozbek.testcontainers.port.updater

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class StringUtilsKtTest {

    @Test
    fun `equalsIgnoringPort successfully detects equality`() {
        val jdbcStringA = "jdbc:postgresql://localhost:55001/test"
        val jdbcStringB = "jdbc:postgresql://localhost:55035/test"
        assertTrue(jdbcStringA.equalsIgnoringPort(jdbcStringB))
    }

    @Test
    fun `equalsIgnoringPort successfully detects inequality`() {
        val jdbcStringA = "jdbc:postgresql://localhost:55001/test"
        val jdbcStringB = "jdbc:sqlserver://localhost:55001/test"
        assertFalse(jdbcStringA.equalsIgnoringPort(jdbcStringB))
    }
}
