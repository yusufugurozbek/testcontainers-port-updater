package com.github.yusufugurozbek.testcontainers.port.updater.services

class DatasourceUrlExtractor {

    private val regex: Regex = "Database: (.*?) \\((.*?)\\)".toRegex()

    internal fun extract(from: String): String? {
        val find = regex.find(from)
        return find?.groupValues?.get(1)
    }
}
