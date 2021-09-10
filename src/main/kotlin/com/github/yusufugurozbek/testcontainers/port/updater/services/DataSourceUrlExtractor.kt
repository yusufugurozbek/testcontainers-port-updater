package com.github.yusufugurozbek.testcontainers.port.updater.services

class DataSourceUrlExtractor {

    private val regex: Regex = "Database: (.*?) \\((.*?)\\)".toRegex()

    internal fun extractDataSourceUrl(text: String): String? {
        val find = regex.find(text)
        return find?.groupValues?.get(1)
    }
}
