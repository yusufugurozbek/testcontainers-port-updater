package com.github.yusufugurozbek.testcontainers.port.updater

class DatasourceUrlExtractor {

    private val regex: Regex = "Database: (.*?)(?!\\S)".toRegex()

    internal fun extract(from: String): String? {
        val find = regex.find(from)
        return find?.groupValues?.get(1)
    }
}
