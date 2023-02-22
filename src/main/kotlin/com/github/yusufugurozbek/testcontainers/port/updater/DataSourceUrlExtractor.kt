package com.github.yusufugurozbek.testcontainers.port.updater

import com.intellij.database.dataSource.LocalDataSource

class DataSourceUrlExtractor {

    companion object {
        private val regex: Regex = "\\s*(?<beforePort>.*):(?<port>\\d{1,5})(?<afterPort>.*;\\S*|\\S*)".toRegex()
    }

    internal fun extract(from: String): DataSourceUrl? {
        val matchResult = regex.find(from)
        return toDataSourceUrl(matchResult)
    }

    internal fun toDataSourceUrl(from: LocalDataSource): DataSourceUrl? {
        if (from.url.isNullOrEmpty()) {
            return null
        }
        val matchResult = regex.find(from.url!!)
        return toDataSourceUrl(matchResult)
    }

    private fun toDataSourceUrl(matchResult: MatchResult?): DataSourceUrl? {
        if (matchResult == null) {
            return null
        }

        val beforePort = matchResult.groups["beforePort"]
        val port = matchResult.groups["port"]

        if (beforePort == null || port == null) {
            return null
        }

        val beforePortValue = beforePort.value
        val portValue = port.value
        val afterPortValue = matchResult.groups["afterPort"]?.value

        return DataSourceUrl(beforePortValue, portValue, afterPortValue)
    }
}
